package com.edutech.progressive.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Transactions;

public class TransactionDAOImpl implements TransactionDAO {

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        String sql = "SELECT * FROM transactions";
        List<Transactions> ans = new ArrayList<>();
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int transaction_id = rs.getInt("transaction_id");
            int account_id = rs.getInt("account_id");
            double amount = rs.getDouble("amount");
            Date transaction_date = rs.getDate("transaction_date");
            String transaction_type = rs.getString("transaction_type");
            Transactions transaction = new Transactions(transaction_id, account_id, amount, transaction_type,
                    transaction_date);
            ans.add(transaction);
        }
        return ans;
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, transactionId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int account_id = rs.getInt("account_id");
            double amount = rs.getDouble("amount");
            Date transaction_date = rs.getDate("transaction_date");
            String transaction_type = rs.getString("transaction_type");
            return new Transactions(transactionId, account_id, amount, transaction_type, transaction_date);

        }
        return null;
    }

    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        String sql = "INSERT INTO transactions(account_id,amount,transaction_date,transaction_type) VALUES (?,?,?,?)";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, transaction.getAccountId());
        ps.setDouble(2, transaction.getAmount());
        ps.setDate(3, transaction.getTransactionDate());
        ps.setString(4, transaction.getTransactionType());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            } else {
                throw new SQLException("Creating transaction failed, no ID obtained.");
            }
        }
        return -1;
    }

    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        String sql = "UPDATE transactions SET account_id=?, amount=?, transaction_date=?, transaction_type=? WHERE transaction_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, transaction.getAccountId());
        ps.setDouble(2, transaction.getAmount());
        ps.setDate(3, transaction.getTransactionDate());
        ps.setString(4, transaction.getTransactionType());
        ps.setInt(5, transaction.getTransactionId());
        ps.executeUpdate();
    }

    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        String sql = "DELETE FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, transactionId);
        ps.executeUpdate();
    }

}
