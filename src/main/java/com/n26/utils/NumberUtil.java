package com.n26.utils;

import com.n26.exception.AmountIsEmptyException;
import com.n26.exception.DecimalFormatParseException;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class NumberUtil {

    public static BigDecimal getBigDecimal (String amount) throws DecimalFormatParseException, AmountIsEmptyException {
        if ( amount==null|| amount.isEmpty())
            throw new AmountIsEmptyException("The input amount is empty");
        try {
            return new BigDecimal(amount).setScale(2, RoundingMode.HALF_UP);
        }
        catch (Exception ex)
        {
            throw new DecimalFormatParseException("The input amount is invalid");
        }
    }
}
