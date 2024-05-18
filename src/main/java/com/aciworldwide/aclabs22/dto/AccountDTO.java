package com.aciworldwide.aclabs22.dto;

import com.aciworldwide.aclabs22.entities.AccountModel;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDTO {

    private String cardNumber;
    private Double amount;
    private Integer dailyTxLimit;
    private Double dailyTxSumLimit;
    private Integer dailyTx;
    private Double dailyTxSum;
    private String status;

    public AccountDTO() {}

    public AccountDTO(AccountModel account){
        cardNumber=account.getCardNumber();
        amount=account.getAmount();
        dailyTxLimit=account.getDailyTxLimit();
        dailyTxSumLimit=account.getDailyTxSumLimit();
        dailyTx=account.getDailyTx();
        dailyTxSum=account.getDailyTxSum();
    }

    public String cardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double amount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer dailyTxLimit() {
        return dailyTxLimit;
    }

    public void setDailyTxLimit(Integer dailyTxLimit) {
        this.dailyTxLimit = dailyTxLimit;
    }

    public Double dailyTxSumLimit() {
        return dailyTxSumLimit;
    }

    public void setDailyTxSumLimit(Double dailyTxSumLimit) {
        this.dailyTxSumLimit = dailyTxSumLimit;
    }

    public Integer dailyTx() {
        return dailyTx;
    }

    public void setDailyTx(Integer dailyTx) {
        this.dailyTx = dailyTx;
    }

    public Double dailyTxSum() {
        return dailyTxSum;
    }

    public void setDailyTxSum(Double dailyTxSum) {
        this.dailyTxSum = dailyTxSum;
    }

    public String status() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
