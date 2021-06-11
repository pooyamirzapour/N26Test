package com.n26.utils;

import com.n26.exception.AmountIsEmptyException;
import com.n26.exception.DecimalFormatParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

public class NumberUtil {

    private final static Logger log = LoggerFactory.getLogger(NumberUtil.class);

    public static BigDecimal getBigDecimal(String amount) throws DecimalFormatParseException, AmountIsEmptyException {
        if (amount == null || amount.isEmpty()) {
            String msg = "The input amount is empty";
            log.error(msg);
            throw new AmountIsEmptyException(msg);
        }
        try {
            return new BigDecimal(amount);
        } catch (Exception ex) {
            String msg = String.format("The input amount is invalid: %s", amount);
            log.error(msg);
            throw new DecimalFormatParseException(msg, ex.getCause());
        }
    }
}
