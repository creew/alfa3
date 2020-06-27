package com.alfa.third.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class NoOfficeFound extends RuntimeException{
    public NoOfficeFound(String message) {
        super(message);
    }
}
