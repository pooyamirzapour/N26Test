package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AmountIsEmptyException extends Exception {

    public AmountIsEmptyException(String message) {
        super(message);
    }

}
