package com.edutech.progressive.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Accounts implements Comparable<Accounts> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int accountId;
    // @ManyToOne
    // @JoinColumn(name = "customerId")
    private int customerId;
    private double balance;

    public Accounts() {
    }

    public Accounts(double balance) {
        this.balance = balance;
    }

    public Accounts(int customerId, double balance) {
        this.customerId = customerId;
        this.balance = balance;
    }

    public Accounts(int accountId, int customerId, double balance) {
        this.accountId = accountId;
        this.customerId = customerId;
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(Accounts o) {
        return Double.compare(this.getBalance(), o.getBalance());
    }

    @Override
    public String toString() {
        return "Accounts [accountId=" + accountId + ", customerId=" + customerId + ", balance=" + balance + "]";
    }

}