package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.exception.AccountNotFoundException;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.repository.TransactionRepository;
import com.edutech.progressive.service.AccountService;

@Service
public class AccountServiceImplJpa implements AccountService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImplJpa(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() throws SQLException {
        return accountRepository.findAll();
    }

    public Accounts getAccountById(int accountId) throws AccountNotFoundException {
        return accountRepository.findById(accountId)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
    }

    @Override
    public int addAccount(Accounts accounts) throws SQLException {
        try {
            return accountRepository.save(accounts).getAccountId();
        } catch (Exception e) {
            return -1;
        }
    }

    public void updateAccount(Accounts accounts) throws SQLException {
        if (accounts == null || !accountRepository.existsById(accounts.getAccountId())) {
            return;
        }
        accountRepository.save(accounts);
    }

    public void deleteAccount(int accountId) throws SQLException {
        accountRepository.deleteById(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() throws SQLException {
        List<Accounts> ans = accountRepository.findAll();
        Collections.sort(ans);
        return ans;
    }

    public List<Accounts> getAccountsByUser(int userId) throws SQLException {
        return accountRepository.findByCustomerId(userId);
    }

}