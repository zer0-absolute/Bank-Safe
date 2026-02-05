package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    public Customers findByCustomerId(int customerId);

    public void deleteByCustomerId(int customerId);

    public Customers findByEmail(String email);
}
