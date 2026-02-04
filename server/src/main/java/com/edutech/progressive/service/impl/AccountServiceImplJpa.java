package com.edutech.progressive.service.impl;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.repository.AccountRepository;
import com.edutech.progressive.service.AccountService;

@Service
public class AccountServiceImplJpa implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public AccountServiceImplJpa(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountRepository.findAll();
    }

    public Accounts getAccountById(int accountId) {
        return accountRepository.findById(accountId).orElse(null);
    }

    @Override
    public int addAccount(Accounts accounts) {
        try {
            return accountRepository.save(accounts).getAccountId();
        } catch (Exception e) {
            return -1;
        }
    }

    public void updateAccount(Accounts accounts) {
        if (accounts == null || !accountRepository.existsById(accounts.getAccountId())) {
            return;
        }
        accountRepository.save(accounts);
    }

    public void deleteAccount(int accountId) {
        accountRepository.deleteById(accountId);
    }

    @Override
    public List<Accounts> getAllAccountsSortedByBalance() {
        List<Accounts> ans = accountRepository.findAll();
        Collections.sort(ans);
        return ans;
    }

    public List<Accounts> getAccountsByUser(int userId) {
        return accountRepository.findByCustomerId(userId);
    }

}