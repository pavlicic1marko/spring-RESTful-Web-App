package com.webApp.springRESTfulWebApp.exceptions.handler;

import com.webApp.springRESTfulWebApp.exceptions.customexceptions.ErrorMessageObject;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserControllerException;
import com.webApp.springRESTfulWebApp.exceptions.customexceptions.UserServiceExceptions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
class AppExceptionHandler {

    @ExceptionHandler(value = {UserControllerException.class})
    public ResponseEntity<Object> handleUserControllerException(UserControllerException ex, WebRequest request) {
        ErrorMessageObject errorMessageObject = new ErrorMessageObject(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessageObject, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value ={UserServiceExceptions.class})
    public ResponseEntity<Object> handelUserServiceExceptions(UserServiceExceptions ex,WebRequest request){
        ErrorMessageObject errorMessageObject = new ErrorMessageObject(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessageObject, new HttpHeaders(), HttpStatus.BAD_REQUEST);

    }

}
