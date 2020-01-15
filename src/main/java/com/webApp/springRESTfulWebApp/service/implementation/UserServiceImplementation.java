package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import data.transfer.objects.AddressDto;
import data.transfer.objects.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
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
            address.setAddressId("test");
            userDto.getAddresses().set(i, address);
        }
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassWord(bCryptPasswordEncoder.encode(userDto.getPassword()));
        UserEntity savedUserEntity = userRepository.save(userEntity);
        return modelMapper.map(savedUserEntity, UserDto.class);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
