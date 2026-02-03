package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;

@Repository
public class AccountDAOImpl implements AccountDAO {
    public Connection connection;

    public AccountDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        String sql = "SELECT * FROM accounts";
        List<Accounts> ans = new ArrayList<>();
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int account_id = rs.getInt("account_id");
            int customer_id = rs.getInt("customer_id");
            double balance = rs.getDouble("balance");
            Accounts acc = new Accounts(account_id, customer_id, balance);
            ans.add(acc);
        }
        return ans;
    }

    public List<Accounts> getAllAccountsByCustomer(int customer_id) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE customer_id=?";
        List<Accounts> ans = new ArrayList<>();
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, customer_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int account_id = rs.getInt("account_id");
            double balance = rs.getDouble("balance");
            Accounts acc = new Accounts(account_id, customer_id, balance);
            ans.add(acc);
        }
        return ans;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int account_id = rs.getInt("account_id");
            int customer_id = rs.getInt("customer_id");
            double balance = rs.getDouble("balance");
            return new Accounts(account_id, customer_id, balance);

        }
        return null;
    }

    @Modifying
    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        String sql = "INSERT INTO accounts(customer_id,balance) VALUES (?,?)";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql,
                Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        int rowsAffected = ps.executeUpdate();
        int generatedID = -1;
        if (rowsAffected > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                generatedID = rs.getInt(1);
                accounts.setAccountId(generatedID);
            } else {
                throw new SQLException("Creating account failed, no ID obtained.");
            }
        }
        return generatedID;
    }

    @Modifying
    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        String sql = "UPDATE accounts SET customer_id=?, balance=? WHERE account_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        ps.setInt(3, accounts.getAccountId());
        ps.executeUpdate();
    }

    @Modifying
    @Override
    public void deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.executeUpdate();
    }

}
