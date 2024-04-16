package com.aciworldwide.aclabs22.entities;

public class Accounts {

    private long id;
    private String cardNumber;
    private double amount;
    private int dailyTxLimit;
    private double dailyTxSumLimit;
    private int dailyTx;
    private double dailyTxSum;

    public Accounts(long id, String cardNumber, int dailyTxLimit, double amount, double dailyTxSumLimit, int dailyTx, double dailyTxSum) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.dailyTxLimit = dailyTxLimit;
        this.amount = amount;
        this.dailyTxSumLimit = dailyTxSumLimit;
        this.dailyTx = dailyTx;
        this.dailyTxSum = dailyTxSum;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
