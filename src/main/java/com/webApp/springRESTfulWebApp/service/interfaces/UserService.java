package com.webApp.springRESTfulWebApp.service.interfaces;

import data.transfer.objects.UserDto;

public interface UserService {
    String getUsers();

    String updateUser();

    String deleteUser();

    UserDto createUser(UserDto userDto);

}
