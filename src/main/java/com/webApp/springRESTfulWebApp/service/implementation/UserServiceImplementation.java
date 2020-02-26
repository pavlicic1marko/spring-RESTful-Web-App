package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.SpringApplicationContext;
import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.dto.ResetPasswordDto;
import com.webApp.springRESTfulWebApp.dto.UpdateUserDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.entities.AddressEntity;
import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserServiceExceptions;
import com.webApp.springRESTfulWebApp.exceptions.messages.ErrorMessages;
import com.webApp.springRESTfulWebApp.repositories.RoleRepository;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.security.AppProperties;
import com.webApp.springRESTfulWebApp.security.Roles;
import com.webApp.springRESTfulWebApp.security.SecurityConstants;
import com.webApp.springRESTfulWebApp.security.UserPrincipalDetails;
import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import com.webApp.springRESTfulWebApp.shared.Utils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Utils utils;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userPage = userRepository.findAll(pageableRequest);
        List<UserDto> userDtoList = new ArrayList<>();

        userPage.forEach(userDtoStream -> userDtoList.add(modelMapper.map(userDtoStream, UserDto.class)));

        return userDtoList;
    }

    @Override
    public UpdateUserDto updateUser(String userId, UserDto userDto) {
        UserEntity  userEntity = Optional.ofNullable(userRepository.findByUserId(userId)).orElseThrow(() -> new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage()));
        String oldEmail = userEntity.getEmail();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        UpdateUserDto returnValue = modelMapper.map(updatedUserEntity, UpdateUserDto.class);

        if(!oldEmail.equals(userDto.getEmail())){
            String newToken = SecurityConstants.TOKEN_PREFIX + Jwts.builder()
                    .setSubject(userDto.getEmail())
                    .setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
                    .signWith(SignatureAlgorithm.HS512, SecurityConstants.getTokenSecret())
                    .compact();
            returnValue.setNewToken(newToken);
        }
        return returnValue;

    }

    @Override
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        userRepository.delete(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity user = userRepository.findByEmail(userDto.getEmail().toLowerCase().trim());
        if (user!=null){
            throw new UserServiceExceptions(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
        }
        for (int i = 0; i < userDto.getAddresses().size(); i++) {
            AddressDto address = userDto.getAddresses().get(i);
            address.setUserDetails(userDto);
            userDto.getAddresses().set(i, address);
        }
        utils.formatUserData(userDto);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        for (AddressEntity addressEntity:userEntity.getAddresses()) {
            addressEntity.setAddressId(UUID.randomUUID().toString());
        }
        userEntity.setEncryptedPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
        userEntity.setAccountEnabled(isAccountEnabledByDefault());

        Collection<RoleEntity> roleEntities = new HashSet<>();
        RoleEntity roleEntity = roleRepository.findByName(Roles.ROLE_USER.name());
        roleEntities.add(roleEntity);
        userEntity.setRoles(roleEntities);
        userEntity.setDateCreated(new Date());


        UserEntity savedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(savedUserEntity, UserDto.class);
    }

    @Override
    public UserDto getUser(String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        userEntity.setAddresses(null);
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);

        return returnValue;
    }

    @Override
    public String restPassword(ResetPasswordDto resetPasswordDto, String email) {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UserServiceExceptions(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        }
        userEntity.setEncryptedPassWord(bCryptPasswordEncoder.encode(resetPasswordDto.getPassword()));
        userRepository.save(userEntity);
        return "Password was updated";
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipalDetails(userEntity);
        //return new User(userEntity.getEmail(), userEntity.getEncryptedPassWord(), new ArrayList<>());
    }

    @Override
    public String activateUserAccount(String userId){
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity.isAccountEnabled()){
            return "account is already active";
        }
        userEntity.setAccountEnabled(true);
        userRepository.save(userEntity);
        return "account is now active";
    }

    @Override
    public String deactivateUserAccount(String userId){
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (!userEntity.isAccountEnabled()) {
            return "account is already deactivated";
        }
        userEntity.setAccountEnabled(false);
        userRepository.save(userEntity);
        return "account is now deactivated";


    }

    private  Boolean isAccountEnabledByDefault() {
        AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
        return utils.ConvertStringToBoolean(appProperties.isAccountEnabledByDefault());
    }
}
