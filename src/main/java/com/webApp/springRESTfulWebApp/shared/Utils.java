package com.webApp.springRESTfulWebApp.shared;

import com.webApp.springRESTfulWebApp.model.request.UserInformationRequestModel;

public class Utils {
    public void checkUserData(UserInformationRequestModel userInformation) {
        if (userInformation.getEmail().isEmpty() || userInformation.getFirstName().isEmpty() || userInformation.getLastName().isEmpty()) {
            throw new RuntimeException("email, first name and last name must not be empty");
        }
    }
}
