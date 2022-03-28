package com.skaczmarek.matchmakerappbackend.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PlayerNotFoundException extends Exception{
    public PlayerNotFoundException(long id){
        super("Player with id: "+ id + " not found.");
    }
    public PlayerNotFoundException(){
        super("Player not found.");
    }
}
