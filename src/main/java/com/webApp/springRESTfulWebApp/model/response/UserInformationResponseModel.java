package com.webApp.springRESTfulWebApp.model.response;

import java.util.List;

public class UserInformationResponseModel {
    private String firstName;
    private String lastName;
    private String email;
    private List<AddressInformationResponseModel> addresses;



    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<AddressInformationResponseModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressInformationResponseModel> addresses) {
        this.addresses = addresses;
    }
}
