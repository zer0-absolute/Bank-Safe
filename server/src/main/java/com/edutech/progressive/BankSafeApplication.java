package com.edutech.progressive;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BankSafeApplication {

    public static void main(String[] args) throws SQLException {
        // Connection conn = DatabaseConnectionManager.getConnection();
        // System.out.println("Welcome to BankSafe Project!");
        // System.out.println(conn);
        // AccountDAOImpl accountDao = new AccountDAOImpl();
        // AccountService as = new AccountServiceImpl(accountDao);
        // CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        // CustomerService cs = new CustomerServiceImpl(customerDAO);
        // Accounts account = new Accounts(22, 1111);
        // Customers customer = new Customers("abs", "DD@gmail.com", "ddabs",
        // "password");
        SpringApplication.run(BankSafeApplication.class, args);
    }
}
