package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;

public class CustomerDAOImpl implements CustomerDAO {
    public static Connection connection;

    public CustomerDAOImpl() {
        try {
            connection = DatabaseConnectionManager.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CustomerDAOImpl(Connection conn) {
        connection = conn;
    }

    @Override
    public List<Customers> getAllCustomers() throws SQLException {
        String sql = "SELECT * FROM customers";
        List<Customers> ans = new ArrayList<>();
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int customer_id = rs.getInt("customer_id");
            String name = rs.getString("name");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String password = rs.getString("password");
            Customers cust = new Customers(customer_id, name, email, username, password);
            ans.add(cust);
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
            Customers cust = new Customers(customerId, name, email, username, password);
            return cust;

        }
        return null;
    }

    @Override
    public int addCustomer(Customers customers) throws SQLException {
        String sql = "INSERT INTO customers(name,email,username,password) VALUES (?,?,?,?)";
        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, customers.getName());
        ps.setString(2, customers.getEmail());
        ps.setString(3, customers.getUsername());
        ps.setString(4, customers.getPassword());
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }
        }
        return -1;
    }

    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        String sql = "UPDATE customers SET name=?, email=?,username=?,password=? WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, customers.getName());
        ps.setString(2, customers.getEmail());
        ps.setString(3, customers.getUsername());
        ps.setString(4, customers.getPassword());
        ps.setInt(5, customers.getCustomerId());
        ps.executeUpdate(sql);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate(sql);
    }

    @Override
    public CustomerAccountInfo getCustomerAccountInfo(int customerId) throws SQLException {
        String sql = "SELECT c.customer_id, c.name, c.email, a.account_id, a.balance FROM customers c LEFT JOIN accounts a ON c.customer_id = a.customer_id WHERE c.customer_id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
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
