package com.edutech.progressive.service.impl;

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
    public List<Customers> getAllCustomers() {
        return cr.findAll();
    }

    public Customers getCustomerById(int customerId) {
        return cr.findByCustomerId(customerId);
    }

    @Override
    public int addCustomer(Customers customers) {
        try {
            return cr.save(customers).getCustomerId();
        } catch (Exception e) {
            return -1;
        }
    }

    public void updateCustomer(Customers customers) {
        if (customers == null || !cr.existsById(customers.getCustomerId())) {
            return;
        }
        cr.save(customers);
    }

    public void deleteCustomer(int customerId) {
        cr.deleteById(customerId);
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() {
        List<Customers> ans = cr.findAll();
        Collections.sort(ans);
        return ans;
    }

}