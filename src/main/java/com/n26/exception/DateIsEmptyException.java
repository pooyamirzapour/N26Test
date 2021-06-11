package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DateIsEmptyException extends Exception{

    public DateIsEmptyException(String message) {
        super(message);
    }

    public DateIsEmptyException(String message, Throwable cause) {
        super(message, cause);
    }
}
