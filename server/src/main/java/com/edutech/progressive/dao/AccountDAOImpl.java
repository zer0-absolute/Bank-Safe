package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;

@Repository
public class AccountDAOImpl implements AccountDAO {
    private List<Accounts> accountsList = new ArrayList<>();
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
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int account_id = rs.getInt("account_id");
            int customer_id = rs.getInt("customer_id");
            double balance = rs.getDouble("balance");
            Accounts account = new Accounts(account_id, customer_id, balance);
            ans.add(account);
        }
        return ans;
    }

    public List<Accounts> getAllAccountsByCustomer(int customer_id) throws SQLException {
        List<Accounts> ans = new ArrayList<>();
        String sql = "SELECT * FROM accounts WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customer_id);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int account_id = rs.getInt("account_id");
            double balance = rs.getDouble("balance");
            Accounts account = new Accounts(account_id, customer_id, balance);
            ans.add(account);
        }
        return ans;
    }

    @Override
    public Accounts getAccountById(int accountId) throws SQLException {
        String sql = "SELECT * FROM accounts WHERE account_id=?";
        Accounts ans = new Accounts();
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            int customer_id = rs.getInt("customer_id");
            double balance = rs.getDouble("balance");
            ans = new Accounts(accountId, customer_id, balance);
        }
        return ans;
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        String sql = "INSERT INTO accounts(customer_id,balance) VALUES(?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setInt(1, accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        int generatedKeys = ps.executeUpdate();
        accounts.setAccountId(generatedKeys);
        return ps.executeUpdate();

    }

    @Override
    public void updateAccount(Accounts accounts) throws SQLException {
        String sql = "UPDATE accounts SET customer_id=?,balance=? WHERE account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accounts.getCustomerId());
        ps.setDouble(2, accounts.getBalance());
        ps.setDouble(3, accounts.getAccountId());
        ps.executeUpdate();
    }

    @Override
    public void deleteAccount(int accountId) throws SQLException {
        String sql = "DELETE FROM accounts WHERE account_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, accountId);
        ps.executeUpdate();
    }

}
