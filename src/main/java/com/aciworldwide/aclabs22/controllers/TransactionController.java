package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.AccountModel;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.repositories.AccountRepository;
import com.aciworldwide.aclabs22.services.AccountService;
import com.aciworldwide.aclabs22.services.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

@RestController
public class TransactionController {
    private static final Logger logs = LoggerFactory.getLogger(TransactionController.class);

    @Autowired
    TransactionService transactionService;

    @Autowired
    AccountRepository accountRepository;

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

    @GetMapping(path = "/accounts/transactions")
    public ResponseEntity<?> getAllTransactions() {
        System.out.println("getAllTransactions");
        return transactionService.getAllTransactions();
    }
    @PostMapping(path="/payment")
    public ResponseEntity<?> processTransaction(@RequestBody TransactionDTO transactionDTO) {
//        Iterable<AccountModel> accounts = accountRepository.findAll();
//        System.out.println();
//        accounts.forEach(System.out::println);
//        AccountModel account = accountRepository.findByCardNumber("1111222233334444");
//        if(account == null){
//            System.out.println("Account not found");
//        }
        logger.info("Processing transaction: "+transactionDTO.getCardNumber()+" with amount:"+transactionDTO.getAmount());
        return transactionService.processTransaction(transactionDTO);

    }


}