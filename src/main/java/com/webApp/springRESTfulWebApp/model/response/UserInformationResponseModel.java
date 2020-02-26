package com.webApp.springRESTfulWebApp.model.response;

import lombok.Data;

import java.util.List;

@Data
public class UserInformationResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressInformationResponseModel> addresses;

}
