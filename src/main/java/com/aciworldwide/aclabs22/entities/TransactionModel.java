package com.aciworldwide.aclabs22.entities;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name="TRANSACTIONS")
public class TransactionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    private Double amount;
    private String cardNumber;
    private String returnCode;
    private Timestamp timestamp;

    public TransactionModel(TransactionDTO transactionDTO) {
        cardNumber = transactionDTO.getCardNumber();
        amount = transactionDTO.getAmount();
        timestamp = new Timestamp(System.currentTimeMillis());
    }

    public TransactionModel() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }
}
