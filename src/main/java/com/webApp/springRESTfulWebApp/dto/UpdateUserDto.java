package com.webApp.springRESTfulWebApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class UpdateUserDto {

    private String firstName;
    private String lastName;
    private String email;
    private String userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String newToken;
    private List<AddressDto> addresses;

}


