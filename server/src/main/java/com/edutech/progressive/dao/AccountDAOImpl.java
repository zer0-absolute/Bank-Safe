package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.repository.AccountRepository;

@Repository
public class AccountDAOImpl implements AccountDAO {
    @Autowired
    AccountRepository ar;
    public Connection connection;

    public AccountDAOImpl() {
        try {
            this.connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return ar.findAll();
    }

    public List<Accounts> getAllAccountsByCustomer(int customer_id) {
        return ar.findByCustomerId(customer_id);
    }

    @Override
    public Accounts getAccountById(int accountId) {
        return ar.findById(accountId).orElse(null);
    }

    @Modifying
    @Override
    public int addAccount(Accounts accounts) {
        return ar.save(accounts).getAccountId();
    }

    @Modifying
    @Override
    public void updateAccount(Accounts accounts) {
        if (ar.existsById(accounts.getAccountId())) {
            ar.save(accounts);
        }
    }

    @Modifying
    @Override
    public void deleteAccount(int accountId) {
        if (ar.existsById(accountId)) {
            ar.deleteById(accountId);
        }
    }

}
