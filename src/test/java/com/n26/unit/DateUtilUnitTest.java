package com.n26.unit;

import com.n26.exception.*;
import com.n26.utils.DateUtil;
import com.n26.utils.NumberUtil;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DateUtilUnitTest {

    @Test
    public void testDateToMilliHappy()throws DateIsInFutureException, OutDatedTransactionException, DateFormatParseException, DateIsEmptyException {
        Instant instant=Instant.now();
        long actual= DateUtil.dateToMilli(instant.toString());
        assertEquals(instant.toEpochMilli(),actual);
    }

    @Test
    public void testDateToMilliIsEmpty(){
        assertThrows(DateIsEmptyException.class,()->DateUtil.dateToMilli(""));
    }

    @Test
    public void testDateToMilliFormatIsInvalid(){
        assertThrows(DateFormatParseException.class,()->DateUtil.dateToMilli("2021-11T12:04:15.312Z"));
    }

    @Test
    public void testDateToMilliFutureDate(){
        assertThrows(DateIsInFutureException.class,()->DateUtil.dateToMilli("2081-02-11T12:04:15.312Z"));
    }

    @Test
    public void testDateToMilliOutDated(){
        assertThrows(OutDatedTransactionException.class,()->DateUtil.dateToMilli("2021-06-11T12:04:15.312Z"));
    }
}
