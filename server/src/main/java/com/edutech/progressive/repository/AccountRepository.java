package com.edutech.progressive.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Accounts;

public interface AccountRepository extends JpaRepository<Accounts, Integer> {
    List<Accounts> findByCustomerId(int customerId);
}
