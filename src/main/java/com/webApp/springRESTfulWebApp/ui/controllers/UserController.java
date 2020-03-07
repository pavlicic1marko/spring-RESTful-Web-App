package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.dto.AddressDto;
import com.webApp.springRESTfulWebApp.dto.ResetPasswordDto;
import com.webApp.springRESTfulWebApp.dto.UpdateUserDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserControllerException;
import com.webApp.springRESTfulWebApp.exceptions.messages.ErrorMessages;
import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;
import com.webApp.springRESTfulWebApp.model.response.AddressInformationResponseModel;
import com.webApp.springRESTfulWebApp.model.response.UserInformationResponseModel;
import com.webApp.springRESTfulWebApp.service.implementation.AddressServiceImplementation;
import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RequestMapping("users")
@RestController
class UserController {

    @Autowired
    private UserServiceImplementation userServiceImplementation;

    @Autowired
    private AddressServiceImplementation addressServiceImplementation;

    private ModelMapper modelMapper = new ModelMapper();

    @GetMapping
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Get information for multiple users Endpoint")
    public List<UserInformationResponseModel> getUsers(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "limit", defaultValue = "20") int limit) {
        List<UserInformationResponseModel> userDetailsList = new ArrayList<>();
        if (page > 0) {
            page -= 1;// if the user enters page 1 it will return the second page (0 is the first page) , with this line page 1 will return the first page
        }
        List<UserDto> userDtoList = userServiceImplementation.getUsers(page, limit);

        userDtoList.forEach(userDtoStream -> userDetailsList.add(modelMapper.map(userDtoStream, UserInformationResponseModel.class)));

        return userDetailsList;
    }

    @GetMapping(path = "/{userId}")
    @PostAuthorize("hasRole('ADMIN') or returnObject.email==principal.username")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Get information for a single users Endpoint")
    UserInformationResponseModel getUser(@PathVariable String userId) {
        UserDto userDto = userServiceImplementation.getUserByUserId(userId);
        return modelMapper.map(userDto, UserInformationResponseModel.class);
    }

    @PostMapping
    @ApiOperation(value = "Create a new user Endpoint")
    UserInformationResponseModel createUser(@RequestBody @Validated UserInformationRequestModel userInformation) {
        if (userInformation.getAddresses() == null) {
            throw new UserControllerException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        UserDto userDto = modelMapper.map(userInformation, UserDto.class);
        UserDto userDtoSavedData = userServiceImplementation.createUser(userDto);
        UserInformationResponseModel returnValue = modelMapper.map(userDtoSavedData, UserInformationResponseModel.class);
        return returnValue;


    }

    @DeleteMapping(path = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN') or #userId==principal.UserId")
    //@Secured("ROLE_ADMIN")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Delete a  user Endpoint")
    UserInformationResponseModel deleteUser(@PathVariable String userId) {
        UserDto returnValue = userServiceImplementation.deleteUser(userId);
        return modelMapper.map(returnValue, UserInformationResponseModel.class);
    }

    @PutMapping(path = "/{userId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Update user information Endpoint")
    UpdateUserDto updateUser(@PathVariable String userId, @RequestBody @Validated UserInformationRequestModel userInformation) {
        UserDto userDto = modelMapper.map(userInformation, UserDto.class);
        return userServiceImplementation.updateUser(userId, userDto);
    }

    @PutMapping(path = "resetpassword/{userName}")
    @PreAuthorize("hasRole('ROLE_ADMIN')or #userName==principal.username")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Reset password")
    public String resetPassword(@PathVariable String userName, @RequestBody ResetPasswordDto resetPasswordDto){
        return userServiceImplementation.restPassword(resetPasswordDto,userName);
    }


    @GetMapping(path = "/{id}/addresses")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Get all addresses for user with provided id")
    List<AddressInformationResponseModel> getUserAddresses(@PathVariable("id") String id) {
        List<AddressDto> addressDtoList = addressServiceImplementation.getAddressesByUserId(id);
        List<AddressInformationResponseModel> returnValue = new ArrayList<>();
        addressDtoList.forEach(address -> returnValue.add(modelMapper.map(address, AddressInformationResponseModel.class)));
        return returnValue;
    }

    @PostMapping(path = "/admin")
    @Secured("ROLE_ADMIN")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Create Admin user")
    UserInformationResponseModel createAdminUser(@RequestBody UserInformationRequestModel userInformationRequestModel){
        if (userInformationRequestModel.getAddresses() == null) {
            throw new UserControllerException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
        }
        UserDto userDto = modelMapper.map(userInformationRequestModel, UserDto.class);
        UserDto userDtoSavedData =userServiceImplementation.createAdminUser(userDto);
        UserInformationResponseModel returnValue = modelMapper.map(userDtoSavedData, UserInformationResponseModel.class);
        return returnValue;


    }

    @GetMapping(path = "searchforuser/{string}")
    @ApiOperation(value = "find all users that have the query string in firstName or lastName or email")
    List<UserInformationResponseModel>  searchForUsers(@PathVariable("string") String string){
        List<UserDto> userDtoList = new ArrayList<>();
        userDtoList = userServiceImplementation.searchForUser(string);
        List<UserInformationResponseModel> returnValue = new ArrayList<>();
        userDtoList.forEach(user -> returnValue.add(modelMapper.map(user, UserInformationResponseModel.class)));
        return returnValue;
    }



}
