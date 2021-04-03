package com.techelevator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.BAD_REQUEST)
public class PhotoNotCreatedException extends Exception{
    private static final long serialVersionUID = 1L;

    public PhotoNotCreatedException(){
        super("Photo Not Created, please check parameters");
    }
}
