package com.webApp.springRESTfulWebApp.model.request;

import lombok.Data;

import java.util.List;

@Data
public class UserInformationRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressInformationRequestModel> addresses;

}
