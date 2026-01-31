package com.edutech.progressive.entity;

public class Accounts implements Comparable<Accounts> {
    private int accountId;
    private int customerId;
    private double balance;

    public Accounts() {
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

}