package com.webApp.springRESTfulWebApp.model.request;

import java.util.List;

public class UserInformationRequestModel {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private List<AddressInformationRequestModel> addresses;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressInformationRequestModel> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressInformationRequestModel> addresses) {
        this.addresses = addresses;
    }
}
