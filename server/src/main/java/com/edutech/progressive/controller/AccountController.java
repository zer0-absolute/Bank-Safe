package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Accounts;
import com.edutech.progressive.service.impl.AccountServiceImplJpa;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountServiceImplJpa asjpa;

    public AccountController(AccountServiceImplJpa asjpa) {
        this.asjpa = asjpa;
    }

    @GetMapping
    public List<Accounts> getAllAccounts() throws SQLException {
        return asjpa.getAllAccounts();
    }

    @GetMapping("/{accountId}")
    public Accounts getAccountById(@PathVariable int accountId) throws SQLException {
        return asjpa.getAccountById(accountId);
    }

    @GetMapping("/users")
    public List<Accounts> getAccountsByUser(@RequestParam String param) throws NumberFormatException, SQLException {
        return asjpa.getAccountsByUser(Integer.parseInt(param));
    }

    @PostMapping("/add")
    public ResponseEntity<Integer> addAccount(@RequestBody Accounts accounts) throws SQLException {
        return new ResponseEntity<>(asjpa.addAccount(accounts), HttpStatus.CREATED);
    }

    @PutMapping("/update/{accountId}")
    public ResponseEntity<Void> updateAccount(@PathVariable int accountId, @RequestBody Accounts accounts)
            throws SQLException {
        asjpa.updateAccount(accounts);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("delete/{accountId}")
    public ResponseEntity<Void> deleteAccount(int accountId) throws SQLException {
        asjpa.deleteAccount(accountId);
        return ResponseEntity.noContent().build();
    }
}