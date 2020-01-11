package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.entityes.UserEntity;
import com.webApp.springRESTfulWebApp.repositories.UserRepository;
import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import data.transfer.objects.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String getUsers() {
        return "this method will return list of all users";
    }

    @Override
    public String updateUser() {
        return "this method will update a user";
    }

    @Override
    public String deleteUser() {
        return "this method will delete a user";
    }

    @Override
    public String createUser(UserDto userDto) {
        ModelMapper modelMapper = new ModelMapper();
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);
        UUID uuid = UUID.randomUUID();
        String userId = uuid.toString();
        userEntity.setUserId(userId);
        userEntity.setEncryptedPassWord("testing");
        userRepository.save(userEntity);
        return "this method will create a user";
    }
}
