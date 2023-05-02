package com.aciworldwide.aclabs22.dto;

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
}
