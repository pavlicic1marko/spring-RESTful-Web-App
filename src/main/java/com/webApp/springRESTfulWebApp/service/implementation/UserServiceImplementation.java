package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import data.transfer.objects.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    ModelMapper modelMapper = new ModelMapper();


    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        UserDto returnValue = modelMapper.map(userEntity, UserDto.class);
        return returnValue;
    }

    @Override
    public List<UserDto> getUsers(int page, int limit) {
        Pageable pageableRequest = PageRequest.of(page, limit);
        Page<UserEntity> userPage = userRepository.findAll(pageableRequest);
        List<UserDto> userDtoList = new ArrayList<>();

        for (UserEntity userEntity : userPage) {
            userDtoList.add(modelMapper.map(userEntity, UserDto.class));
        }
        return userDtoList;
    }

    @Override
    public UserDto updateUser(String userId, UserDto userDto) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userEntity.setEmail(userDto.getEmail());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        UserEntity updatedUserEntity = userRepository.save(userEntity);
        UserDto returnValue = modelMapper.map(updatedUserEntity, UserDto.class);
        return returnValue;
    }

    @Override
    public String deleteUser(String userId) {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if (userEntity == null) {
            throw new RuntimeException("There is no user with provided User Id");
        }
        userRepository.delete(userEntity);
        return "user with id:" + userId + " was deleted";
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassWord("testing");
        UserEntity savedUserEntity = userRepository.save(userEntity);
        UserDto returnValues = modelMapper.map(savedUserEntity, UserDto.class);
        return returnValues;
    }
}
