package com.edutech.progressive.dao;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Transactions;

@Repository
public class TransactionDAOImpl implements TransactionDAO {
    public Connection connection;

    public TransactionDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Transactions> getAllTransactions() throws SQLException {
        String sql = "SELECT * FROM transactions";
        List<Transactions> ans = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int transaction_id = rs.getInt("transaction_id");
            int account_id = rs.getInt("account_id");
            double amount = rs.getDouble("amount");
            Date transaction_date = rs.getDate("transaction_date");
            String transaction_type = rs.getString("transaction_type");
            Transactions transaction = new Transactions(transaction_id, account_id, amount,
                    transaction_date, transaction_type);
            ans.add(transaction);
        }
        return ans;
    }

    @Override
    public Transactions getTransactionById(int transactionId) throws SQLException {
        String sql = "SELECT * FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transactionId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int account_id = rs.getInt("account_id");
            double amount = rs.getDouble("amount");
            Date transaction_date = rs.getDate("transaction_date");
            String transaction_type = rs.getString("transaction_type");
            return new Transactions(transactionId, account_id, amount, transaction_date, transaction_type);

        }
        return null;
    }

    @Modifying
    @Override
    public int addTransaction(Transactions transaction) throws SQLException {
        String sql = "INSERT INTO transactions(account_id,amount,transaction_date,transaction_type) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, transaction.getAccountId());
        ps.setDouble(2, transaction.getAmount());
        ps.setDate(3, new Date(transaction.getTransactionDate().getTime()));
        ps.setString(4, transaction.getTransactionType());
        int rowsAffected = ps.executeUpdate();
        int generatedID = -1;
        if (rowsAffected > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedID = rs.getInt(1);
                transaction.setTransactionId(generatedID);
                return generatedID;
            } else {
                throw new SQLException("Creating transaction failed, no ID obtained.");
            }
        }
        return -1;
    }

    @Modifying
    @Override
    public void updateTransaction(Transactions transaction) throws SQLException {
        String sql = "UPDATE transactions SET account_id=?, amount=?, transaction_date=?, transaction_type=? WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transaction.getAccountId());
        ps.setDouble(2, transaction.getAmount());
        ps.setDate(3, new Date(transaction.getTransactionDate().getTime()));
        ps.setString(4, transaction.getTransactionType());
        ps.setInt(5, transaction.getTransactionId());
        ps.executeUpdate();
    }

    @Modifying
    @Override
    public void deleteTransaction(int transactionId) throws SQLException {
        String sql = "DELETE FROM transactions WHERE transaction_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, transactionId);
        ps.executeUpdate();
    }

}
