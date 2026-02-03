package com.edutech.progressive.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.dao.AccountDAO;
import com.edutech.progressive.dao.AccountDAOImpl;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountDAO accountDAO;

    public AccountServiceImpl(AccountDAOImpl accountDAO) {
        this.accountDAO = accountDAO;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountDAO.getAllAccounts();
    }

    public Accounts getAccountById(int accountId) {
        return accountDAO.getAccountById(accountId);
    }

    @Override
    public int addAccount(Accounts accounts) {
        return accountDAO.addAccount(accounts);
    }

    public void updateAccount(Accounts accounts) {
        accountDAO.updateAccount(accounts);
    }

    public void deleteAccount(int accountId) {
        accountDAO.deleteAccount(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() {
        List<Accounts> ans = accountDAO.getAllAccounts();
        Collections.sort(ans);
        return ans;
    }

    public List<Accounts> getAccountsByUser(int userId) {
        return accountDAO.getAllAccountsByCustomer(userId);
    }

}