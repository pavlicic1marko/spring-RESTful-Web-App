package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import data.transfer.objects.UserDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;


    @GetMapping
    public String getUsers() {
        return userServiceImplementation.getUsers();
    }

    @PostMapping
    public UserInformationResponseModel createUser(@RequestBody UserInformationRequestModel userInformation) {
        ModelMapper modelMapper = new ModelMapper();
        UserDto userDto = modelMapper.map(userInformation,UserDto.class);
        UserDto userDtoSavedData= userServiceImplementation.createUser(userDto);
        UserInformationResponseModel returnValue = modelMapper.map(userDtoSavedData,UserInformationResponseModel.class);
        return returnValue;


    }

    @DeleteMapping
    public String deleteUser() {
        return userServiceImplementation.deleteUser();
    }

    @PutMapping
    public String updateUser() {
        return userServiceImplementation.updateUser();
    }


}
