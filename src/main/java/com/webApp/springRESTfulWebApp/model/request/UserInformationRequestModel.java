package com.webApp.springRESTfulWebApp.model.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
public class UserInformationRequestModel {
    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @Email(message = "Email should be valid")
    private String email;
    private String password;
    private List<AddressInformationRequestModel> addresses;

}
