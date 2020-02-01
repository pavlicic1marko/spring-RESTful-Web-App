package com.webApp.springRESTfulWebApp.shared;

import com.webApp.springRESTfulWebApp.dto.UserDto;
import org.springframework.stereotype.Component;


@Component
public class Utils {

    public void formatUserData(UserDto userDto){
        userDto.setEmail(userDto.getEmail().toLowerCase().trim());
    }
}
