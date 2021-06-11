package com.n26.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllExceptionHandler {

    @ExceptionHandler(AmountIsEmptyException.class)
    public ResponseEntity<?> amountIsEmptyException() {
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DateFormatParseException.class)
    public ResponseEntity<?> dateFormatParseException() {
        return new ResponseEntity<>("", HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(DateIsEmptyException.class)
    public ResponseEntity<?> dateIsEmptyException() {
        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
}
