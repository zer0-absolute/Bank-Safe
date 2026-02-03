package com.edutech.progressive.dao;

import java.sql.*;
import java.util.*;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.edutech.progressive.config.DatabaseConnectionManager;
import com.edutech.progressive.dto.CustomerAccountInfo;
import com.edutech.progressive.entity.Customers;

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
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
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
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
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

    @Modifying
    @Override
    public int addCustomer(Customers customers) throws SQLException {
        try (Connection connection = DatabaseConnectionManager.getConnection();
                PreparedStatement statement = connection.prepareStatement(
                        "INSERT INTO customers (name,email,username,password) VALUES (?,?,?,?)",
                        PreparedStatement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, customers.getName());
            statement.setString(2, customers.getEmail());
            statement.setString(3, customers.getUsername());
            statement.setString(4, customers.getPassword());
            statement.executeUpdate();
            int generatedID = -1;
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedID = generatedKeys.getInt(1);
                    customers.setCustomerId(generatedID);
                }
            }
            return generatedID;
        }
    }

    @Modifying
    @Override
    public void updateCustomer(Customers customers) throws SQLException {
        String sql = "UPDATE customers SET name=?, email=?,username=?,password=? WHERE customer_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setString(1, customers.getName());
        ps.setString(2, customers.getEmail());
        ps.setString(3, customers.getUsername());
        ps.setString(4, customers.getPassword());
        ps.setInt(5, customers.getCustomerId());
        ps.executeUpdate();
    }

    @Modifying
    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        PreparedStatement ps = DatabaseConnectionManager.getConnection().prepareStatement(sql);
        ps.setInt(1, customerId);
        ps.executeUpdate();
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
