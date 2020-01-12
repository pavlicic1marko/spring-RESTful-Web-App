package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import data.transfer.objects.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    ModelMapper modelMapper = new ModelMapper();


    @GetMapping
    public String getUsers() {
        return userServiceImplementation.getUsers();
    }

    @GetMapping(path = "/{userId}")
    public UserInformationResponseModel getUser(@PathVariable String userId) {
        UserDto userDto = userServiceImplementation.getUserByUserId(userId);
        UserInformationResponseModel returnValue = modelMapper.map(userDto, UserInformationResponseModel.class);
        return returnValue;
    }

    @PostMapping
    public UserInformationResponseModel createUser(@RequestBody UserInformationRequestModel userInformation) {
        UserDto userDto = modelMapper.map(userInformation, UserDto.class);
        UserDto userDtoSavedData = userServiceImplementation.createUser(userDto);
        UserInformationResponseModel returnValue = modelMapper.map(userDtoSavedData, UserInformationResponseModel.class);
        return returnValue;


    }

    @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String deleteUser(@PathVariable String userId) {
        return userServiceImplementation.deleteUser(userId);
    }

    @PutMapping
    public String updateUser() {
        return userServiceImplementation.updateUser();
    }


}
