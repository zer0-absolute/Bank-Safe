package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Transactions;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    // List<Transactions> getTransactionsByCustomerId;
    // @Query("select t from Transactions t where t.account.customerId=
    // :customerId")
    // findByAccountsCustomerId();
}
