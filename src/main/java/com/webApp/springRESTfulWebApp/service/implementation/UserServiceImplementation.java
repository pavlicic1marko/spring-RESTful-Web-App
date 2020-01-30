package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.entities.RoleEntity;
import com.webApp.springRESTfulWebApp.entities.UserEntity;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserServiceExceptions;
import com.webApp.springRESTfulWebApp.exceptions.messages.ErrorMessages;
import com.webApp.springRESTfulWebApp.repositories.RoleRepository;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.security.UserPrincipalDetails;
import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.User;
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
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(updatedUserEntity, UserDto.class);
    }

    @Override
    public UserDto deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new RuntimeException("There is no user with provided User Id");
        }
        userRepository.delete(userEntity);
        return modelMapper.map(userEntity, UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        for (int i = 0; i < userDto.getAddresses().size(); i++) {
            AddressDto address = userDto.getAddresses().get(i);
            address.setUserDetails(userDto);
            address.setAddressId(UUID.randomUUID().toString());
            userDto.getAddresses().set(i, address);
        }
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        userEntity.setUserId(UUID.randomUUID().toString());
        userEntity.setEncryptedPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
        
        Collection<RoleEntity> roleEntities = new HashSet<>();
        RoleEntity roleEntity = roleRepository.findByName("ROLE_USER");
        roleEntities.add(roleEntity);
        userEntity.setRoles(roleEntities);


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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        return new UserPrincipalDetails(userEntity);
        //return new User(userEntity.getEmail(), userEntity.getEncryptedPassWord(), new ArrayList<>());
    }
}
