package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.*;

import com.edutech.progressive.dao.CustomerDAO;
import com.edutech.progressive.dao.CustomerDAOImpl;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;

public class CustomerServiceImpl implements CustomerService {
    private CustomerDAO customerDAO;
    private static List<Customers> customersList = new ArrayList<>();

    public CustomerServiceImpl(CustomerDAOImpl customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return customerDAO.getAllCustomers();
    }

    public Customers getCustomerById(int customerId) throws SQLException {
        return customerDAO.getCustomerById(customerId);
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        return customerDAO.addCustomer(customers);
    }

    public void updateCustomer(Customers customers) throws SQLException {
        customerDAO.updateCustomer(customers);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        customerDAO.deleteCustomer(customerId);
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> ans = customerDAO.getAllCustomers();
        Collections.sort(ans);
        return ans;
    }

}