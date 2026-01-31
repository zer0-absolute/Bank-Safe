package com.edutech.progressive.dao;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Transactions;

public interface TransactionDAO {
    public List<Transactions> getAllTransactions() throws SQLException;

    public Transactions getTransactionById(int transactionId) throws SQLException;

    public int addTransaction(Transactions transaction) throws SQLException;

    public void updateTransaction(Transactions transaction) throws SQLException;

    public void deleteTransaction(int transactionId) throws SQLException;
}
