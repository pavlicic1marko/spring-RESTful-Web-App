package com.webApp.springRESTfulWebApp.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class AddressDto {

    private String addressId;

    private String city;

    private String streetName;

    private String streetNumber;

    private String addressType;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UserDto userDetails;

}


