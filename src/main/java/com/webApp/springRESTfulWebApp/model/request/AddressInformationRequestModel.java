package com.webApp.springRESTfulWebApp.model.request;

import lombok.Data;

@Data
public class AddressInformationRequestModel {

    private String addressId;

    private String city;

    private String streetName;

    private String streetNumber;

    private String addressType;

}
