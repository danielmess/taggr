package com.techelevator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Photo Not Found")
public class PhotoNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public PhotoNotFoundException(){
        super("Photo Not Found");
    }
}
