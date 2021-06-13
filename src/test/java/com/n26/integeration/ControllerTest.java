
package com.n26.integeration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.n26.model.add.TransactionDTO;
import com.n26.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private TransactionService transactionService;

    public ControllerTest() {
    }

    @Test
    public void testSaveHappy() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount("1234.12");
        Instant instant = Instant.now();
        transactionDTO.setTime(instant.toString());
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").
                content((new ObjectMapper()).writeValueAsString(transactionDTO)).header("Content-Type",
                MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testSaveInvalidAmount() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount("dd");
        Instant instant = Instant.now();
        transactionDTO.setTime(instant.toString());
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").content((new ObjectMapper()).writeValueAsString(transactionDTO))
                .header("Content-Type", MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testSaveOutedDate() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount("1234.1");
        transactionDTO.setTime("2021-06-11T12:04:15.312Z");
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").
                content((new ObjectMapper()).writeValueAsString(transactionDTO))
                .header("Content-Type", MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testSaveDateIsInFuture() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount("1234.1");
        transactionDTO.setTime("2081-06-11T12:04:15.312Z");
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").
                content((new ObjectMapper()).writeValueAsString(transactionDTO)).header("Content-Type",
                MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void testSaveDateBadFormat() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount("1234.1");
        transactionDTO.setTime("2081-11T12:04:15.312Z");
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").content((new ObjectMapper()).writeValueAsString(transactionDTO))
                .header("Content-Type", MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    public void tetsSaveAmountIsEmpty() throws Exception {
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setTime("2081-11T12:04:15.312Z");
        this.mvc.perform(MockMvcRequestBuilders.post("/api/v1/transactions").
                content((new ObjectMapper()).writeValueAsString(transactionDTO)).header("Content-Type",
                MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void testClearTransactionsHappy() throws Exception {
        this.mvc.perform(MockMvcRequestBuilders.delete("/api/v1/transactions").
                header("Content-Type", MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    public void testGetHappy() throws Exception {
        transactionService.remove();
        Instant instant = Instant.now();
        TransactionDTO transactionDTO1 = new TransactionDTO(instant.toString(), "4");
        Instant instant1 = instant.plusMillis(10);
        TransactionDTO transactionDTO2 = new TransactionDTO(instant1.toString(), "6");
        transactionService.save(transactionDTO1);
        Thread.sleep(100);
        transactionService.save(transactionDTO2);

        this.mvc.perform(MockMvcRequestBuilders.get("/api/v1/statistics").
                header("Content-Type", MediaType.APPLICATION_JSON)).
                andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.avg").value(5))
                .andExpect(jsonPath("$.sum").value(10))
                .andExpect(jsonPath("$.max").value(6))
                .andExpect(jsonPath("$.min").value(4))
                .andExpect(jsonPath("$.count").value(2));

    }
}
