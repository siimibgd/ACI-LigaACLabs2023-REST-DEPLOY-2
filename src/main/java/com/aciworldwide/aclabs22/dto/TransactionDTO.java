package com.aciworldwide.aclabs22.dto;

import com.aciworldwide.aclabs22.entities.Transactions;

import java.sql.Timestamp;

public class TransactionDTO {

    private String cardNumber;
    private Double amount;
    private Timestamp timestamp;
    private String returnCode;

    public TransactionDTO() {}

    public TransactionDTO(Transactions transaction) {
        cardNumber=transaction.cardNumber();
        amount=transaction.amount();
        timestamp=transaction.timestamp();
        returnCode=transaction.returnCode();
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }



    public String getCardNumber() {
        return cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public String getReturnCode() {
        return returnCode;
    }


}
