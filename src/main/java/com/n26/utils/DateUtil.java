package com.n26.utils;

import com.n26.exception.DateFormatParseException;
import com.n26.exception.DateIsFutureException;
import com.n26.exception.OutDatedTransactionException;

import java.text.ParseException;
import java.time.Instant;

public class DateUtil {

    public static long dateToMilli(String strDate) throws DateFormatParseException, DateIsFutureException, OutDatedTransactionException {
        Instant instant;
        try {
             instant = Instant.parse(strDate);
        }
        catch (Exception ex)
        {
            throw new DateFormatParseException("The input timestamp is invalid");
        }
        if (instant.toEpochMilli()> Instant.now().toEpochMilli())
            throw new DateIsFutureException("The transaction is for future");
        long diff=  (Instant.now().toEpochMilli() -instant.toEpochMilli())/1000;
        if (diff>60)
            throw new OutDatedTransactionException("The transaction is expired");
        return  instant.toEpochMilli();


    }
}
