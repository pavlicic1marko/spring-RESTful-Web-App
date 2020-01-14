package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import com.webApp.springRESTfulWebApp.shared.Utils;
import data.transfer.objects.UserDto;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("users")
@RestController
public class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    private ModelMapper modelMapper = new ModelMapper();

    private Utils utils = new Utils();



    @ApiOperation(value = "Get information for multiple users Endpoint")
    @GetMapping
    public List<UserInformationResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<UserInformationResponseModel> userDetailsList = new ArrayList<>();
        List<UserDto> userDtoList = userServiceImplementation.getUsers(page, limit);

        userDtoList.forEach(userDtoStream -> userDetailsList.add(modelMapper.map(userDtoStream, UserInformationResponseModel.class)));

        return userDetailsList;
    }

    @ApiOperation(value = "Get information for a single users Endpoint")
    @GetMapping(path = "/{userId}")
    public UserInformationResponseModel getUser(@PathVariable String userId) {
        UserDto userDto = userServiceImplementation.getUserByUserId(userId);
        return modelMapper.map(userDto, UserInformationResponseModel.class);
    }

    @ApiOperation(value = "Create a new user Endpoint")
    @PostMapping
    public UserInformationResponseModel createUser(@RequestBody UserInformationRequestModel userInformation) {
        utils.checkUserData(userInformation);
        UserDto userDto = modelMapper.map(userInformation, UserDto.class);
        UserDto userDtoSavedData = userServiceImplementation.createUser(userDto);
        return modelMapper.map(userDtoSavedData, UserInformationResponseModel.class);


    }

    @ApiOperation(value = "Delete a  user Endpoint")
    @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInformationResponseModel deleteUser(@PathVariable String userId) {
        UserDto returnValue = userServiceImplementation.deleteUser(userId);
        return modelMapper.map(returnValue, UserInformationResponseModel.class);
    }

    @ApiOperation(value = "Update user information Endpoint")
    @PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public UserInformationResponseModel updateUser(@PathVariable String userId, @RequestBody UserInformationRequestModel userInformation) {
        utils.checkUserData(userInformation);
        UserDto userDto = modelMapper.map(userInformation, UserDto.class);
        UserDto updatedValue = userServiceImplementation.updateUser(userId, userDto);
        return modelMapper.map(updatedValue, UserInformationResponseModel.class);
    }


}
