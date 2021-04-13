package com.csts.restfulcrud.exception;

public class UserNotExistException extends RuntimeException{
    public UserNotExistException() {
        super("User not exist");
    }
}
