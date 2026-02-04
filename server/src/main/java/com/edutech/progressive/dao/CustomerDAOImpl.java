package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;
import com.edutech.progressive.repository.CustomerRepository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    public Connection connection;

    public CustomerDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        List<Customers> ans = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customerId = rs.getInt("customer_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            Customers customer = new Customers(customerId, name, email, username, password);
            ans.add(customer);
        }
        return ans;
    }

    @Override
    public Customers getCustomerById(int customerId) throws SQLException {
        String sql = "SELECT * FROM customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            return new Customers(customerId, name, email, username, password);
        }
        return null;
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        String sql = "INSERT INTO customers(name, email, username, password) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customers.getName());
        ps.setString(2, customers.getEmail());
        ps.setString(3, customers.getUsername());
        ps.setString(4, customers.getPassword());
        ps.executeUpdate();
        ResultSet generatedKeys = ps.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedID = generatedKeys.getInt(1);
            customers.setCustomerId(generatedID);
            return generatedID;
        }
        return -1;
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        String sql = "UPDATE customers SET name=?, email=?, username=?, password=? WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customers.getName());
        ps.setString(2, customers.getEmail());
        ps.setString(3, customers.getUsername());
        ps.setString(4, customers.getPassword());
        ps.setInt(5, customers.getCustomerId());
        ps.executeUpdate();
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerId);
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
