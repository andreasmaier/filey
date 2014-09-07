package com.token.exceptions;

public class ErrorInfo {

    private String attribute;
    private String message;

    public ErrorInfo(String attribute, Exception exc){
        this.attribute = attribute;
        this.message = exc.getLocalizedMessage();
    }

    public String getAttribute() {
        return attribute;
    }

    public String getMessage() {
        return message;
    }
}
