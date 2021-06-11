package com.n26.unit;

import com.n26.exception.AmountIsEmptyException;
import com.n26.exception.DecimalFormatParseException;
import com.n26.utils.NumberUtil;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class NumberUtilUnitTest {

    @Test
    public void testGetBigDecimalHappy() throws AmountIsEmptyException, DecimalFormatParseException {
        BigDecimal actual = NumberUtil.getBigDecimal("1234.1234");
        assertEquals(new BigDecimal("1234.1234"), actual);
    }

    @Test
    public void testGetBigDecimalInvalidStringFormat() {
        assertThrows(DecimalFormatParseException.class, () -> NumberUtil.getBigDecimal("sss"));
    }

    @Test
    public void testGetBigDecimalEmpty() {
        assertThrows(AmountIsEmptyException.class, () -> NumberUtil.getBigDecimal(""));
    }
}