package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.AccountService;
import com.edutech.progressive.service.impl.AccountServiceImpl;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountService as;

    public AccountController(AccountServiceImpl as) {
        this.as = as;
    }

    @GetMapping
    public List<Accounts> getAllAccounts() throws SQLException {
        return as.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public Accounts getAccountById(@PathVariable int accountId) throws SQLException {
        return as.getAccountById(accountId);
    }

    @GetMapping("/users")
    public List<Accounts> getAccountsByUser(@RequestParam String param) throws NumberFormatException, SQLException {
        return as.getAccountsByUser(Integer.parseInt(param));
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addAccount(@RequestBody Accounts accounts) throws SQLException {
        return new ResponseEntity<>(as.addAccount(accounts), HttpStatus.CREATED);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable int accountId, @RequestBody Accounts accounts)
            throws SQLException {
        as.updateAccount(accounts);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(int accountId) throws SQLException {
        as.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}