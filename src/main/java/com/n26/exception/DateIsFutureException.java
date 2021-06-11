package com.n26.exception;

public class DateIsFutureException extends Exception {

    public DateIsFutureException(String message) {
        super(message);
    }

    public DateIsFutureException(String message, Throwable cause) {
        super(message, cause);
    }
}
