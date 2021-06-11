package com.n26.model.get;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Statistics {

    private double avg;
    private double sum;
    private double max;
    private double min;
    private long count;

    @Builder
    public Statistics(double avg, double sum,double max,double min,long count) {
        this.avg = avg;
        this.sum = sum;
        this.max = max;
        this.min = min;
        this.count = count;

    }

}
