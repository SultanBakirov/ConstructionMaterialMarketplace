package com.example.constructionmaterialmarketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidOrderStateException extends RuntimeException {


    public InvalidOrderStateException() {
        super();
    }

    public InvalidOrderStateException(String message) {
        super(message);
    }
}
