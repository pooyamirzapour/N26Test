package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DecimalFormatParseException extends Exception {
    public DecimalFormatParseException(String message) {
        super(message);
    }

    public DecimalFormatParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
