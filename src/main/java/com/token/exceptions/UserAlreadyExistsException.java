package com.token.exceptions;

public class UserAlreadyExistsException extends Exception {
    private String username;

    public UserAlreadyExistsException(String username) {
        super("User '" + username + "' already exists.");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
