package com.adena.edhukanuserservice.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String Message){
        super(Message);
    }
}
