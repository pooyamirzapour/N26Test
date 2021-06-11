package com.n26.service;

import com.n26.exception.*;
import com.n26.model.add.TransactionDTO;
import com.n26.model.get.Statistics;
import com.n26.utils.DateUtil;
import com.n26.utils.NumberUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TransactionServiceImpl implements TransactionService {


    ConcurrentHashMap<Long, BigDecimal> concurrentHashMap = new ConcurrentHashMap<>();


    @Override
    public synchronized ResponseEntity save(TransactionDTO transactionDTO) throws AmountIsEmptyException, DecimalFormatParseException, OutDatedTransactionException, DateFormatParseException, DateIsEmptyException, DateIsFutureException {
        BigDecimal amount = NumberUtil.getBigDecimal(transactionDTO.getAmount());
        long time = DateUtil.dateToMilli(transactionDTO.getTime());
        concurrentHashMap.put(time, amount);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Override
    public synchronized void remove() {
        concurrentHashMap.clear();
    }

    @Override
    public synchronized Statistics get() {
        return null;
    }
}
