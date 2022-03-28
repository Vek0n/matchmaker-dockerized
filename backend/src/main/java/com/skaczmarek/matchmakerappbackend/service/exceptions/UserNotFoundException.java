package com.skaczmarek.matchmakerappbackend.service.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends Exception{
    public UserNotFoundException(long id){
        super("User with id: "+ id + " not found.");
    }
}