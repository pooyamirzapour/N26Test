package com.n26.model.add;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDTO {

    @JsonProperty("timestamp")
    private String time;
    @JsonProperty("amount")
    private String amount;

    public TransactionDTO(String time, String amount) {
        this.time = time;
        this.amount = amount;
    }

    public TransactionDTO() {
    }
}
