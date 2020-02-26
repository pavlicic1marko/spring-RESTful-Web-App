package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("UserAccountStatus")
@RestController
public class UserAccountEnabledController {

    @Autowired
    UserServiceImplementation userServiceImplementation;

    @PostMapping(path="/Activate/{userId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Activate user account With specified user ID")
    public String activateUserAccount(@PathVariable String userId){
        return userServiceImplementation.activateUserAccount(userId);
    }

    @PostMapping(path="/Deactivate/{userId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "authorization", value = "${userController.authorizationHeader.description}", paramType = "header")})
    @ApiOperation(value = "Deactivate user account With specified user ID")
    public  String deactivateUserAccount(@PathVariable String userId){
        return userServiceImplementation.deactivateUserAccount(userId);
    }

}
