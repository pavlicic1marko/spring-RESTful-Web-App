package com.webApp.springRESTfulWebApp.service.implementation;

import com.webApp.springRESTfulWebApp.service.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplementation implements UserService {

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
    public String createUser() {
        return "this method will create a user";
    }
}
