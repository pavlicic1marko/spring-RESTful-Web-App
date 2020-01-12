package com.webApp.springRESTfulWebApp.service.interfaces;

import data.transfer.objects.UserDto;

public interface UserService {
    String getUsers();

    String updateUser();

    String deleteUser(String userId);

    UserDto createUser(UserDto userDto);

}
