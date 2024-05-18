package com.aciworldwide.aclabs22.entities;

import java.sql.Timestamp;

public class Transactions {

    private long id;
    private String cardNumber;
    private Double amount;
    private Timestamp timestamp;
    private String returnCode;

    public long id() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double amount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String cardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Timestamp timestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String returnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
