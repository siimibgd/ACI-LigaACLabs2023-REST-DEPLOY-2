package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.services.AccountService;
import com.aciworldwide.aclabs22.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@ControllerAdvice
@RestController
@RequestMapping(path="/payment")
public class TransactionController {
    private static final Logger logs = LoggerFactory.getLogger(TransactionController.class);

//    @Autowired
//    TransactionService transactionService;
    private BlockingQueue<TransactionDTO> accountQueue=new ArrayBlockingQueue<>(10);
    public void start(){
        Thread producerThread = new Thread(new AccountService());
        Thread consumerThread = new Thread(new TransactionService());

        producerThread.start();
        consumerThread.start();
    }
    @PostMapping
    public ResponseEntity<?> processTransaction(@RequestBody TransactionDTO transactionDTO) {
        TransactionService transactionService=new TransactionService();
        transactionService.processTransaction(transactionDTO);
        return null;

    }
}