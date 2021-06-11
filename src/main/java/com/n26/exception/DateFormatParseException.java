package com.n26.exception;

public class DateFormatParseException extends Exception{
    public DateFormatParseException(String message) {
        super(message);
    }

    public DateFormatParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
