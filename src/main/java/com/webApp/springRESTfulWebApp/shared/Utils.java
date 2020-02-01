package com.webApp.springRESTfulWebApp.shared;

import com.webApp.springRESTfulWebApp.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public class Utils {
    public void checkUserData(UserDto userDto) {
        if (userDto.getEmail().isEmpty() || userDto.getFirstName().isEmpty() || userDto.getLastName().isEmpty()) {
            throw new RuntimeException("email, first name and last name must not be empty");
        }
    }

    public void formatUserData(UserDto userDto){
        userDto.setEmail(userDto.getEmail().toLowerCase().trim());
    }
}
