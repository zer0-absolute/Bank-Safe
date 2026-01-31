package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.*;

import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.dao.AccountDAOImpl;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDAO accountDAO;
    // private static List<Accounts> accountsList = new ArrayList<>();

    public AccountServiceImpl(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountDAO.getAllAccounts();
    }

    public Accounts getAccountById(int accountId) throws SQLException {
        return accountDAO.getAccountById(accountId);
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        return accountDAO.addAccount(accounts);
    }

    public void updateAccount(Accounts accounts) throws SQLException {
        accountDAO.updateAccount(accounts);
    }

    public void deleteAccount(int accountId) throws SQLException {
        accountDAO.deleteAccount(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> ans = accountDAO.getAllAccounts();
        Collections.sort(ans);
        return ans;
    }

    public List<Accounts> getAccountsByUser(int userId) throws SQLException {
        return accountDAO.getAllAccountsByCustomer(userId);
    }

}