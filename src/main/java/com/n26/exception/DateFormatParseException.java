package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
public class DateFormatParseException extends Exception{

    public DateFormatParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
