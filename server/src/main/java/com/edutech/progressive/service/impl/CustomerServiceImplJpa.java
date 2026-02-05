package com.edutech.progressive.service.impl;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.repository.CustomerRepository;
import com.edutech.progressive.service.CustomerService;

@Service
public class CustomerServiceImplJpa implements CustomerService {
    @Autowired
    private CustomerRepository cr;

    public CustomerServiceImplJpa(CustomerRepository cr) {
        this.cr = cr;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        return cr.findAll();
    }

    public Customers getCustomerById(int customerId) throws SQLException {
        return cr.findByCustomerId(customerId);
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        try {
            return cr.save(customers).getCustomerId();
        } catch (Exception e) {
            return -1;
        }
    }

    public void updateCustomer(Customers customers) throws SQLException {
        if (customers == null || !cr.existsById(customers.getCustomerId())) {
            return;
        }
        cr.save(customers);
    }

    public void deleteCustomer(int customerId) throws SQLException {
        cr.deleteById(customerId);
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() throws SQLException {
        List<Customers> ans = cr.findAll();
        Collections.sort(ans);
        return ans;
    }

}