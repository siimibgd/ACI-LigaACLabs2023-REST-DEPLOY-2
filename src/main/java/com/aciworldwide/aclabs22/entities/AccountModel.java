package com.aciworldwide.aclabs22.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="ACCOUNTS")
public class AccountModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    private String cardNumber;
    private double amount;
    private int dailyTxLimit;
    private double dailyTxSumLimit;
    private int dailyTx;
    private double dailyTxSum;
    private String cardHolderName;
}
