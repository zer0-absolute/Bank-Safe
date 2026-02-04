package com.edutech.progressive.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edutech.progressive.entity.Customers;

public interface CustomerRepository extends JpaRepository<Customers, Integer> {
    Customers findByCustomerId(int customerId);

    void deleteByCustomerId(int customerId);

}
