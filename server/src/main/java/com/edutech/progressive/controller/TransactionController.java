package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.service.impl.AccountServiceImplJpa;
import com.edutech.progressive.service.impl.TransactionServiceImplJpa;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    private final TransactionServiceImplJpa transactionServiceImplJpa;
    @Autowired
    private final AccountServiceImplJpa accountServiceImplJpa;

    public TransactionController(TransactionServiceImplJpa transactionServiceImplJpa,
            AccountServiceImplJpa accountServiceImplJpa) {
        this.transactionServiceImplJpa = transactionServiceImplJpa;
        this.accountServiceImplJpa = accountServiceImplJpa;
    }

    @GetMapping()
    public ResponseEntity<List<Transactions>> getAllTransactions() throws SQLException {
        return new ResponseEntity<>(transactionServiceImplJpa.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transactions> getTransactionById(@PathVariable int transactionId) throws SQLException {
        return new ResponseEntity<>(transactionServiceImplJpa.getTransactionById(transactionId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> addTransaction(@RequestBody Transactions transaction) throws SQLException {
        return new ResponseEntity<>(transactionServiceImplJpa.addTransaction(transaction), HttpStatus.CREATED);
    }

    @PutMapping("/{transactionId}")
    public ResponseEntity<Void> updateTransaction(@PathVariable int transactionId,
            @RequestBody Transactions transaction) throws SQLException {
        transactionServiceImplJpa.updateTransaction(transaction);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Void> deleteTransaction(int transactionId) throws SQLException {
        transactionServiceImplJpa.deleteTransaction(transactionId);
        return ResponseEntity.noContent().build();
    }
}
