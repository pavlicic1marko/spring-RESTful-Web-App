package com.webApp.springRESTfulWebApp.exceptions.customexceptions;

public class UserControllerException extends RuntimeException {

    public UserControllerException(String errorMessage) {
        super(errorMessage);
    }
}
