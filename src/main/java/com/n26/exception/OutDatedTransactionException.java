package com.n26.exception;

public class OutDatedTransactionException extends Exception{
    public OutDatedTransactionException(String message) {
        super(message);
    }

    public OutDatedTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
