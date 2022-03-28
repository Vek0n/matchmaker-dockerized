package com.skaczmarek.matchmakerappbackend.service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RoomIsClosedException extends Exception{
    public RoomIsClosedException(long id){
            super("Room with id: "+ id + " is closed.");
        }
}

