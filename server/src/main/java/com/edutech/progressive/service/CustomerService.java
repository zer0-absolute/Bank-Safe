package com.edutech.progressive.service;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.entity.Customers;

public interface CustomerService {
    public List<Customers> getAllCustomers() throws SQLException;

    public int addCustomer(Customers customers) throws SQLException;

    public List<Customers> getAllCustomersSortedByName() throws SQLException;

    default public void emptyArrayList() {
    }

    // Do not implement these methods in CustomerServiceImplArraylist.java class
    default void updateCustomer(Customers customers) throws SQLException {
    }

    default void deleteCustomer(int customerId) throws SQLException {
    }

    default Customers getCustomerById(int customerId) throws SQLException {
        return null;
    }

}