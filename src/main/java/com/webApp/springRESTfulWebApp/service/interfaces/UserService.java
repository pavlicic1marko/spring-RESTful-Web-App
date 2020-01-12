package com.webApp.springRESTfulWebApp.service.interfaces;

import data.transfer.objects.UserDto;

public interface UserService {
    UserDto getUserByUserId(String userID);

    String getUsers();

    UserDto updateUser(String userId, UserDto userDto);

    String deleteUser(String userId);

    UserDto createUser(UserDto userDto);

}
