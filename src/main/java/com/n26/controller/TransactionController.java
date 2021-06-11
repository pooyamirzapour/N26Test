package com.n26.controller;

import com.n26.model.add.TransactionDTO;
import com.n26.model.get.Statistics;
import com.n26.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@Component
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/transactions")
    public ResponseEntity<String> save(@Valid @RequestBody TransactionDTO transactionDTO) throws Exception {
        return transactionService.save(transactionDTO);
       // return new ResponseEntity<>("",save.getStatusCode());

    }

    @DeleteMapping("/transactions")
    public ResponseEntity<String> remove()  {
        transactionService.remove();
        return ResponseEntity.ok("ok");
    }


    @GetMapping("/statistics")
    public ResponseEntity<Statistics> get() throws Exception {
        Statistics Statistics = transactionService.get();
        return new ResponseEntity<>(Statistics, HttpStatus.OK);
    }

}
