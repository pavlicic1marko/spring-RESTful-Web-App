package com.webApp.springRESTfulWebApp.custom.exceptions;

public class UserControllerException extends RuntimeException {

    public UserControllerException(String errorMessage) {
        super(errorMessage);
    }
}
