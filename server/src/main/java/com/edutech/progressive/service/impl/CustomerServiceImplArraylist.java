package com.edutech.progressive.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Service;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;

@Service
public class CustomerServiceImplArraylist implements CustomerService {
    private static List<Customers> customersList = new ArrayList<>();

    @Override
    public List<Customers> getAllCustomers() {
        return customersList;
    }

    @Override
    public int addCustomer(Customers customers) {
        customersList.add(customers);
        return customersList.size();
    }

    @Override
    public List<Customers> getAllCustomersSortedByName() {
        Collections.sort(customersList);
        return customersList;
    }

    @Override
    public void emptyArrayList() {
        customersList = new ArrayList<>();
    }

}