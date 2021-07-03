package com.n26.unit;

import com.n26.exception.*;
import com.n26.model.add.Transaction;
import com.n26.model.add.TransactionDTO;
import com.n26.model.get.Statistics;
import com.n26.service.TransactionService;
import com.n26.service.TransactionServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.time.Instant;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@Import(TransactionServiceImpl.class)
@RunWith(SpringRunner.class)
public class TransactionServiceTest {
    @Mock
    private TransactionService mockTransactionService;

    @Autowired
    private TransactionService transactionService;


    @Test
    public void testSaveHappy() throws DateIsInFutureException, AmountIsEmptyException, OutDatedTransactionException, DecimalFormatParseException, ParseException, DateFormatParseException, DateIsEmptyException {
        Instant instant = Instant.now();
        TransactionDTO transactionDTO = new TransactionDTO(instant.toString(), "1234.1234");
        ResponseEntity save = transactionService.save(transactionDTO);
        assertEquals(HttpStatus.CREATED, save.getStatusCode());
    }

    @Test
    public void testSaveEmptyAmount() {
        Instant instant = Instant.now();
        TransactionDTO transactionDTO = new TransactionDTO(instant.toString(), "");
        assertThrows(AmountIsEmptyException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testSaveEmptyDate() {
        TransactionDTO transactionDTO = new TransactionDTO("", "1234.1234");
        assertThrows(DateIsEmptyException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testSaveDateIsInFuture() {
        TransactionDTO transactionDTO = new TransactionDTO("2081-06-11T12:04:15.312Z", "1234.1234");
        assertThrows(DateIsInFutureException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testSaveDateFormatParseException() {
        TransactionDTO transactionDTO = new TransactionDTO("2081-11T12:04:15.312Z", "1234.1234");
        assertThrows(DateFormatParseException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testSaveDecimalFormatParseException() {
        Instant instant = Instant.now();
        TransactionDTO transactionDTO = new TransactionDTO(instant.toString(), "dddd");
        assertThrows(DecimalFormatParseException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testSaveOutDatedTransaction() {
        TransactionDTO transactionDTO = new TransactionDTO("2020-06-11T12:04:15.312Z", "1234.1234");
        assertThrows(OutDatedTransactionException.class, () -> transactionService.save(transactionDTO));
    }

    @Test
    public void testRemoveTransaction() {
        doNothing().when(mockTransactionService).remove();
        mockTransactionService.remove();
        verify(mockTransactionService, times(1)).remove();
    }

    @Test
    public void testGetTransaction() throws DateIsInFutureException, AmountIsEmptyException, OutDatedTransactionException, DecimalFormatParseException, ParseException, DateFormatParseException, DateIsEmptyException, InterruptedException {
        transactionService.remove();
        Instant instant = Instant.now();
        TransactionDTO transactionDTO1 = new TransactionDTO(instant.toString(), "4");
        Thread.sleep(100);
        Instant instant1 = instant.plusMillis(10);
        TransactionDTO transactionDTO2 = new TransactionDTO(instant1.toString(), "6");
        transactionService.save(transactionDTO1);
        transactionService.save(transactionDTO2);
        Statistics statistics = transactionService.getStatistics();

        assertEquals(statistics.getCount(), 2);
        assertEquals( Double.valueOf(6.0).doubleValue(),statistics.getMax(),0);
        assertEquals( Double.valueOf(4).doubleValue(),statistics.getMin(),0);
        assertEquals( Double.valueOf(10).doubleValue(),statistics.getSum(),0);
        assertEquals( Double.valueOf(5).doubleValue(),statistics.getAvg(),0);

    }



}
