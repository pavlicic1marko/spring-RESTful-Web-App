package com.webApp.springRESTfulWebApp.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AddressInformationResponseModel {

    @JsonIgnore
    private String addressId;

    private String city;

    private String streetName;

    private String streetNumber;

    private String addressType;

}
