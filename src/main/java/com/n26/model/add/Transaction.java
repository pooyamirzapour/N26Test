package com.n26.model.add;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Optional;

@Data
@Builder
public class Transaction {
    private long time;
    private BigDecimal amount;

    @Builder
    public Transaction(long time, BigDecimal amount) {
        this.time = time;
        this.amount = amount;

    }

}
