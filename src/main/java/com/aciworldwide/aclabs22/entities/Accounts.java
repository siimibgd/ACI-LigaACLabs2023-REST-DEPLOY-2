package com.aciworldwide.aclabs22.entities;

public class Accounts {
    final long id;
    final String cardNumber;
    double amount;
    int dailyTxLimit;
    double dailyTxSumLimit;
    int dailyTx;
    double dailyTxSum;

    public Accounts(long id, String cardNumber, double amount, int dailyTxLimit, double dailyTxSumLimit, int dailyTx, double dailyTxSum) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.amount = amount;
        this.dailyTxLimit = dailyTxLimit;
        this.dailyTxSumLimit = dailyTxSumLimit;
        this.dailyTx = dailyTx;
        this.dailyTxSum = dailyTxSum;
    }

    public long id() {
        return id;
    }


    public double amount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount -= amount;
    }

    public String cardNumber() {
        return cardNumber;
    }


    public int dailyTxLimit() {
        return dailyTxLimit;
    }


    public double dailyTxSumLimit() {
        return dailyTxSumLimit;
    }

    public int dailyTx() {
        return dailyTx;
    }

    public void setDailyTx() {
        dailyTx++;
    }

    public double dailyTxSum() {
        return dailyTxSum;
    }

    public void setDailyTxSum(double dailyTxSum) {
        this.dailyTxSum += dailyTxSum;
    }
}
