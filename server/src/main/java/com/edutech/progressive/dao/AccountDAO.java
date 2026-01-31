package com.edutech.progressive.dao;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Accounts;

public interface AccountDAO {
    public int addAccount(Accounts accounts) throws SQLException;

    public Accounts getAccountById(int accountId) throws SQLException;

    public void updateAccount(Accounts accounts) throws SQLException;

    public void deleteAccount(int accountId) throws SQLException;

    public List<Accounts> getAllAccounts() throws SQLException;

    public List<Accounts> getAllAccountsByCustomer(int userId) throws SQLException;

}