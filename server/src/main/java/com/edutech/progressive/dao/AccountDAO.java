package com.edutech.progressive.dao;

import java.util.List;

import com.edutech.progressive.entity.Accounts;

public interface AccountDAO {
    public int addAccount(Accounts accounts);

    public Accounts getAccountById(int accountId);

    public void updateAccount(Accounts accounts);

    public void deleteAccount(int accountId);

    public List<Accounts> getAllAccounts();

    public List<Accounts> getAllAccountsByCustomer(int userId) ;

}