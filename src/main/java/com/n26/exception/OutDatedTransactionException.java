package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NO_CONTENT)
public class OutDatedTransactionException extends Exception{
    public OutDatedTransactionException(String message) {
        super(message);
    }

}
