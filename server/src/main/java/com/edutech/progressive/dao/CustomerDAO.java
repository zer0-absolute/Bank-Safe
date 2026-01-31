package com.edutech.progressive.dao;

import java.sql.SQLException;
import java.util.List;

import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;

public interface CustomerDAO {
    public Customers getCustomerById(int customerId) throws SQLException;
    public int addCustomer(Customers customers) throws SQLException;
    public void updateCustomer(Customers customers) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException;
    public List<Customers> getAllCustomers() throws SQLException;
}