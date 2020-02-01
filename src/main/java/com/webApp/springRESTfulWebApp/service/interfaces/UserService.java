package com.webApp.springRESTfulWebApp.service.interfaces;

import com.webApp.springRESTfulWebApp.dto.ResetPasswordDto;
import com.webApp.springRESTfulWebApp.dto.UpdateUserDto;
import com.webApp.springRESTfulWebApp.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    UserDto getUserByUserId(String userID);

    List<UserDto> getUsers(int page, int limit);

    UpdateUserDto updateUser(String userId, UserDto userDto);

    UserDto deleteUser(String userId);

    UserDto createUser(UserDto userDto);

    UserDto getUser(String email);

    String restPassword(ResetPasswordDto resetPasswordDto,String userID);

}
