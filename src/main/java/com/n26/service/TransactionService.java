package com.n26.service;

import com.n26.exception.DateFormatParseException;
import com.n26.exception.DecimalFormatParseException;
import com.n26.model.add.TransactionDTO;
import com.n26.model.get.Statistics;
import org.springframework.http.ResponseEntity;

import java.text.ParseException;

public interface TransactionService {

    ResponseEntity save(TransactionDTO transactionDTO) throws ParseException, DateFormatParseException, DecimalFormatParseException;

    void remove();

    Statistics get();
}
