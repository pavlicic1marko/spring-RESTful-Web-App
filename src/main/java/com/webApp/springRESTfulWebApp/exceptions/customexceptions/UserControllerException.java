package com.webApp.springRESTfulWebApp.exceptions.customexceptions;

public class UserControllerException extends RuntimeException {

    private static final long serialVersionUID = 8297646481826983834L;

    public UserControllerException(String errorMessage) {
        super(errorMessage);
    }
}
