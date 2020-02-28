package com.webApp.springRESTfulWebApp.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String userId;
    private List<AddressDto> addresses;

    @Override
    public String toString() {
        return "UserDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
