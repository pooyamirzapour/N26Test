package com.n26.exception;

public class DecimalFormatParseException extends Exception {
    public DecimalFormatParseException(String message) {
        super(message);
    }

    public DecimalFormatParseException(String message, Throwable cause) {
        super(message, cause);
    }
}
