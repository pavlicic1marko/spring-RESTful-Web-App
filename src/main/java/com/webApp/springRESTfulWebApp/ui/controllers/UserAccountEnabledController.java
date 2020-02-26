package com.webApp.springRESTfulWebApp.ui.controllers;

import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
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
    public String activateUserAccount(@PathVariable String userId){
        return userServiceImplementation.activateUserAccount(userId);
    }

    @PostMapping(path="/Deactivate/{userId}")
    public  String deactivateUserAccount(@PathVariable String userId){
        return userServiceImplementation.deactivateUserAccount(userId);
    }

}
