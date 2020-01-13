package com.webApp.springRESTfulWebApp.service.interfaces;

import data.transfer.objects.UserDto;

import java.util.List;

public interface UserService {
    UserDto getUserByUserId(String userID);

    List<UserDto> getUsers(int page, int limit);

    UserDto updateUser(String userId, UserDto userDto);

    String deleteUser(String userId);

    UserDto createUser(UserDto userDto);

}
