package com.n26.unit;

import com.n26.model.get.Statistics;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class StatisticsUnitTest {

    @Test
    public void testBuildStatistics() {
        Statistics result = Statistics.builder()
                .avg(new Double(10))
                .max(new Double(10))
                .min(new Double(10))
                .sum(new Double(10))
                .count(1l).build();
        assertNotNull(result);


    }
}
