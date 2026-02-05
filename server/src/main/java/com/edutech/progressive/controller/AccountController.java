package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.exception.AccountNotFoundException;
import com.edutech.progressive.service.impl.AccountServiceImplJpa;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private final AccountServiceImplJpa accountServiceImplJpa;

    public AccountController(AccountServiceImplJpa accountServiceImplJpa) {
        this.accountServiceImplJpa = accountServiceImplJpa;
    }

    @GetMapping()
    public ResponseEntity<List<Accounts>> getAllAccounts() throws SQLException {
        return new ResponseEntity<>(accountServiceImplJpa.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<?> getAccountById(@PathVariable int accountId) throws AccountNotFoundException {
        try {
            return new ResponseEntity<>(accountServiceImplJpa.getAccountById(accountId), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Account not found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Accounts>> getAccountsByUser(@PathVariable int userId) throws SQLException {
        return new ResponseEntity<>(accountServiceImplJpa.getAccountsByUser(userId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Integer> addAccount(@RequestBody Accounts accounts) throws SQLException {
        return new ResponseEntity<>(accountServiceImplJpa.addAccount(accounts), HttpStatus.CREATED);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable int accountId, @RequestBody Accounts accounts)
            throws SQLException {
        accountServiceImplJpa.updateAccount(accounts);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(int accountId) throws SQLException {
        accountServiceImplJpa.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}