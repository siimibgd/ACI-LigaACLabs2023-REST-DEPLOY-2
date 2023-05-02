package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class TransactionController {

    @Autowired
    TransactionService transactionService;

    public ResponseEntity<?> processTransaction() {
        return transactionService.processTransaction();
    }
}