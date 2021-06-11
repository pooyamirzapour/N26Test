package com.n26.unit;

import com.n26.model.add.Transaction;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertNotNull;

public class TransactionDTOUnitTest {
    @Test
   public void testBuildTransaction() {
        Transaction result = Transaction.builder().amount(BigDecimal.TEN).time(1623412764610L).build();
        assertNotNull(result);
    }
}
