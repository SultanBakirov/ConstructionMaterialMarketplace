package com.example.constructionmaterialmarketplace.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidStatusUpdateException extends RuntimeException {

    public InvalidStatusUpdateException() {
        super();
    }

    public InvalidStatusUpdateException(String message) {
        super(message);
    }
}
