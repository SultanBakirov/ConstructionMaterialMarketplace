package com.example.constructionmaterialmarketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.MULTI_STATUS)
public class InvalidStatusTransitionException extends RuntimeException {

    public InvalidStatusTransitionException() {
        super();
    }

    public InvalidStatusTransitionException(String message) {
        super(message);
    }
}
