package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DateIsInFutureException extends Exception {

    public DateIsInFutureException(String message) {
        super(message);
    }

}
