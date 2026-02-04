package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Transactions;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.repository.TransactionRepository;
import com.edutech.progressive.service.TransactionService;

@Service
public class TransactionServiceImplJpa implements TransactionService {
    @Autowired
    private TransactionRepository tr;
    @Autowired
    private AccountRepository ar;

    public TransactionServiceImplJpa() {
    }

    public TransactionServiceImplJpa(TransactionRepository tr) {
        this.tr = tr;
    }

    public TransactionServiceImplJpa(AccountRepository ar) {
        this.ar = ar;
    }

    public TransactionServiceImplJpa(TransactionRepository tr, AccountRepository ar) {
        this.tr = tr;
        this.ar = ar;
    }

    public List<Transactions> getAllTransactions() throws SQLException {
        return tr.findAll();
    }

    public Transactions getTransactionById(int transactionId) throws SQLException {
        return tr.findById(transactionId).orElse(null);
    }

    public int addTransaction(Transactions transaction) throws SQLException {
        try {
            return tr.save(transaction).getTransactionId();
        } catch (Exception e) {
            return -1;
        }
    }

    public void updateTransaction(Transactions transaction) throws SQLException {
        if (tr.existsById(transaction.getTransactionId())) {
            tr.save(transaction);
        }
    }

    public void deleteTransaction(int transactionId) {
        tr.deleteById(transactionId);
    }

    @Override
    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException {
        return null;
    }

}
