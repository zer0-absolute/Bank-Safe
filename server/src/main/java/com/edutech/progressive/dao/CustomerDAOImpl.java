package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.repository.CustomerRepository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    CustomerRepository cr;
    public Connection connection;

    public CustomerDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customers> getAllCustomers() {
        return cr.findAll();
    }

    @Override
    public Customers getCustomerById(int customerId) {
        return cr.findById(customerId).orElse(null);
    }

    @Modifying
    @Override
    public int addCustomer(Customers customers) {
        return cr.save(customers).getCustomerId();
    }

    @Modifying
    @Override
    public void updateCustomer(Customers customers) {
        if (cr.existsById(customers.getCustomerId())) {
            cr.save(customers);
        }
    }

    @Modifying
    @Override
    public void deleteCustomer(int customerId) {
        if (cr.existsById(customerId)) {
            cr.deleteById(customerId);
        }
    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException {
        String sql = "SELECT c.customer_id, c.name, c.email, a.account_id, a.balance FROM customers c LEFT JOIN accounts a ON c.customer_id = a.customer_id WHERE c.customer_id = ?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            int account_id = rs.getInt("account_id");
            double balance = rs.getDouble("balance");
            return new CustomerAccountInfo(customerId, name, email, account_id, balance);
        }
        return null;
    }

}
