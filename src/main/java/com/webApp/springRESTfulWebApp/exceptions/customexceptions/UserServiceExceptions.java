package com.webApp.springRESTfulWebApp.exceptions.customexceptions;

public class UserServiceExceptions extends RuntimeException {

    private static final long serialVersionUID = -1595769747360884594L;

    public UserServiceExceptions(String errorMessage) {
        super(errorMessage);
    }
}
