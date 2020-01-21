package com.webApp.springRESTfulWebApp.custom.exceptions;

public class UserServiceExceptions extends RuntimeException {

    private static final long serialVersionUID = -1595769747360884594L;

    UserServiceExceptions(String errorMessage) {
        super(errorMessage);
    }
}
