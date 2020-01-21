package com.webApp.springRESTfulWebApp.exceptions.customexceptions;

import java.util.Date;

public class ErrorMessageObject {
    private String message;
    private Date timestamp;

    public ErrorMessageObject() {
    }

    public ErrorMessageObject(Date timestamp, String message) {
        this.timestamp = timestamp;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }
}
