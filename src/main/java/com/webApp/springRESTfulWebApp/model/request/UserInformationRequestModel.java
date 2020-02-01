package com.webApp.springRESTfulWebApp.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import java.util.List;

@Data
public class UserInformationRequestModel {
    private String firstName;
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private List<AddressInformationRequestModel> addresses;

}
