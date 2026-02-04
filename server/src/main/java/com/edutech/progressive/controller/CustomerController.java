package com.edutech.progressive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.service.CustomerService;
import com.edutech.progressive.service.impl.CustomerServiceImplArraylist;
import com.edutech.progressive.service.impl.CustomerServiceImplJpa;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {
    // @Autowired
    private CustomerService cs;
    // @Autowired
    private CustomerServiceImplArraylist csal;
    @Autowired
    private CustomerServiceImplJpa csjpa;

    @GetMapping()
    public List<Customers> getAllCustomers() throws SQLException {
        return csjpa.getAllCustomers();
    }

    @GetMapping("/{customerID}")
    public Customers getCustomerById(@PathVariable int customerID) throws SQLException {
        return csjpa.getCustomerById(customerID);
    }

    @GetMapping("/fromArrayList")
    public List<Customers> getAllCustomersFromArrayList() {
        return csal.getAllCustomers();
    }

    @GetMapping("/fromArrayList/all")
    public List<Customers> getAllCustomersSortedByNameFromArrayList() {
        return csal.getAllCustomersSortedByName();
    }

    @PostMapping()
    public ResponseEntity<Integer> addCustomer(@RequestBody Customers customers) {
        return new ResponseEntity<>(csjpa.addCustomer(customers), HttpStatus.CREATED);
    }

    @PostMapping("/toArrayList")
    public ResponseEntity<Integer> addCustomerToArrayList(Customers customers) {
        return new ResponseEntity<>(csal.addCustomer(customers), HttpStatus.CREATED);
    }

    @PutMapping("/{customerID}")
    public void updateCustomer(@PathVariable int customerID, @RequestBody Customers customers) {
        csjpa.updateCustomer(customers);
    }

    @DeleteMapping("/{customerID}")
    public void deleteCustomer(int customerId) throws SQLException {
        csjpa.deleteCustomer(customerId);
    }

}
