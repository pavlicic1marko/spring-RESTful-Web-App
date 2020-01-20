package com.webApp.springRESTfulWebApp.service.interfaces;

import data.transfer.objects.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto getUserByUserId(String userID);

    List<UserDto> getUsers(int page, int limit);

    UserDto updateUser(String userId, UserDto userDto);

    UserDto deleteUser(String userId);

    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

}
