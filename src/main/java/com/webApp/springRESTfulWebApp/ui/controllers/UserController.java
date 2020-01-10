package com.webApp.springRESTfulWebApp.ui.controllers;


import com.webApp.springRESTfulWebApp.service.implementation.UserServiceImplementation;
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
    public String createUser() {
        return userServiceImplementation.createUser();
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
