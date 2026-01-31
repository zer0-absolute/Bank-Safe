package com.edutech.progressive;

import java.sql.Connection;
import java.sql.SQLException;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dao.AccountDAOImpl;
import com.edutech.progressive.dao.CustomerDAOImpl;
import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.AccountService;
import com.edutech.progressive.service.CustomerService;
import com.edutech.progressive.service.impl.AccountServiceImpl;
import com.edutech.progressive.service.impl.CustomerServiceImpl;

public class BankSafeApplication {
    public static void main(String[] args) throws SQLException {
        Connection conn = DatabaseConnectionManager.getConnection();
        System.out.println("Welcome to BankSafe Project!");
        System.out.println(conn);
        AccountDAOImpl accountDao = new AccountDAOImpl();
        AccountService as = new AccountServiceImpl(accountDao);
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        CustomerService cs = new CustomerServiceImpl(customerDAO);
        Accounts account = new Accounts(22, 1111);
        Customers customer = new Customers("abs", "DD@gmail.com", "ddabs", "password");
    }
}
