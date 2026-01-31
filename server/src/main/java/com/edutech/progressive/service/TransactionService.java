package com.edutech.progressive.service;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Transactions;

public interface TransactionService {
    public List<Transactions> getAllTransactions() throws SQLException;

    public Transactions getTransactionById(int transactionId) throws SQLException;

    public int addTransaction(Transactions transaction) throws SQLException;

    public void updateTransaction(Transactions transaction) throws SQLException;

    public void deleteTransaction(int transactionId) throws SQLException;

    public List<Transactions> getTransactionsByCustomerId(int customerId) throws SQLException;
}