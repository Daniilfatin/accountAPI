package com.example.accountAPI.exception;

public class UserAlreadyExistException extends Exception {
    public UserAlreadyExistException (String message) {
        super(message);
    }
}
