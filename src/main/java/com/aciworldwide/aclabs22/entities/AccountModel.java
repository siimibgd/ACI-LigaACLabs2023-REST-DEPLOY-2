package com.aciworldwide.aclabs22.entities;

import com.aciworldwide.aclabs22.dto.AccountDTO;
import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "ACCOUNTS")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String cardNumber;
    private String cardHolderName;
    private double amount;
    private int dailyTxLimit;
    private double dailyTxSumLimit;
    private int dailyTx;
    private double dailyTxSum;

    public AccountModel() {}

    public AccountModel(AccountDTO accountDTO) {
        cardNumber=accountDTO.cardNumber();
        amount=accountDTO.amount();
        dailyTxLimit=accountDTO.dailyTxLimit();
        dailyTxSumLimit=accountDTO.dailyTxSumLimit();
        dailyTx=accountDTO.dailyTx();
        dailyTxSum=accountDTO.dailyTxSum();
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
    public String getCardHolderName() {
        return cardHolderName;
    }
    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }
    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }
    public int getDailyTxLimit() {
        return dailyTxLimit;
    }
    public void setDailyTxLimit(int dailyTxLimit) {
        this.dailyTxLimit = dailyTxLimit;
    }
    public double getDailyTxSumLimit() {
        return dailyTxSumLimit;
    }
    public void setDailyTxSumLimit(double dailyTxSumLimit) {
        this.dailyTxSumLimit = dailyTxSumLimit;
    }
    public int getDailyTx() {
        return dailyTx;
    }
    public void setDailyTx() {
        this.dailyTx++;
    }
    public double getDailyTxSum() {
        return dailyTxSum;
    }
    public void setDailyTxSum(double dailyTxSum) {
        this.dailyTxSum+= dailyTxSum;
    }

}
