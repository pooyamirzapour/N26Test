package com.n26.utils;

import com.n26.exception.DateFormatParseException;
import com.n26.exception.DateIsEmptyException;
import com.n26.exception.DateIsFutureException;
import com.n26.exception.OutDatedTransactionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Instant;

public class DateUtil {

    private final static Logger log = LoggerFactory.getLogger(DateUtil.class);


    public static long dateToMilli(String strDate) throws DateFormatParseException, DateIsFutureException, OutDatedTransactionException, DateIsEmptyException {
        if (strDate == null || strDate.isEmpty()) {
            String msg = "The input timestamp is empty";
            log.error(msg);
            throw new DateIsEmptyException(msg);
        }
        Instant instant;
        try {
            instant = Instant.parse(strDate);
        } catch (Exception ex) {
            String msg = String.format("The input timestamp is invalid: %s", strDate);
            log.error(msg);
            throw new DateFormatParseException(msg);
        }
        if (instant.toEpochMilli() > Instant.now().toEpochMilli()) {
            String msg = String.format("The transaction is for future: %s", strDate);
            log.error(msg);
            throw new DateIsFutureException(msg);
        }
        long diff = (Instant.now().toEpochMilli() - instant.toEpochMilli()) / 1000;
        if (diff > 60) {
            String msg = String.format("he transaction is expired: %s", diff);
            log.error(msg);
            throw new OutDatedTransactionException(msg);
        }
        return instant.toEpochMilli();


    }
}
