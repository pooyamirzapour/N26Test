package com.n26.unit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.model.add.Transaction;
import com.n26.model.add.TransactionDTO;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TransactionDTOUnitTest {
    @Test
   public void testBuildTransaction() throws IOException {
        String jsonAsString = "{\"timestamp\":\"2021-06-11T12:04:15.312Z\",\"amount\":\"1234.1234\"}";
        ObjectMapper mapper = new ObjectMapper();
        TransactionDTO actual = mapper.readValue(jsonAsString, TransactionDTO.class);
        TransactionDTO expected = TransactionDTO.builder().amount("1234.1234").time("2021-06-11T12:04:15.312Z").build();
        assertEquals(expected,actual);
    }
}
