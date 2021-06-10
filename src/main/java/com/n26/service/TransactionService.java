package com.n26.service;

import com.n26.model.add.NewTransactionRequest;
import com.n26.model.get.StatisticsResponse;

public interface TransactionService {

    void add(NewTransactionRequest newTransactionRequest);

    void remove();

    StatisticsResponse get();
}
