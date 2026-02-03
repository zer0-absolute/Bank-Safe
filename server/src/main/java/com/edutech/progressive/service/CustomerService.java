package com.edutech.progressive.service;

import java.util.List;

import com.edutech.progressive.entity.Customers;

public interface CustomerService {
    public List<Customers> getAllCustomers();

    public int addCustomer(Customers customers);

    public List<Customers> getAllCustomersSortedByName();

    default public void emptyArrayList() {
    }

    // Do not implement these methods in CustomerServiceImplArraylist.java class
    default void updateCustomer(Customers customers) {
    }

    default void deleteCustomer(int customerId) {
    }

    default Customers getCustomerById(int customerId) {
        return null;
    }

}