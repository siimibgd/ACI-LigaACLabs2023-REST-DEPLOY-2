package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(path="/payment")
public class TransactionController {
    private static final Logger logger=LogManager.getLogger();
    private static Map<String, Accounts> createListOfCards(){
        Map<String, Accounts> accountMap = new HashMap<>();

        Accounts account1 = new Accounts(1, "9000111100001121", 5, 500.0, 0, 0, 250);
        Accounts account2 = new Accounts(2, "1111222233334444", 6, 550.0, 0, 0, 250);
        Accounts account3 = new Accounts(3, "4321000000056789", 7, 600.0, 0, 0, 300);
        Accounts account4 = new Accounts(4, "4444000000000001", 8, 650.0, 0, 0, 400);
        Accounts account5 = new Accounts(5, "9000000000000000", 9, 700.0, 0, 0, 250);
        Accounts account6 = new Accounts(6, "9218740563280417", 10, 750.0, 0, 0, 250);
        Accounts account7 = new Accounts(7, "5489371026750348", 11, 800.0, 0, 0, 300);
        Accounts account8 = new Accounts(8, "3095871620497532", 12, 850.0, 0, 0, 250);
        Accounts account9 = new Accounts(9, "8267591432085761", 13, 900.0, 0, 0, 250);
        Accounts account10 = new Accounts(10, "1234000000056789", 14, 950.0, 0, 0, 800);

        accountMap.put(account1.getCardNumber(), account1);
        accountMap.put(account2.getCardNumber(), account2);
        accountMap.put(account3.getCardNumber(), account3);
        accountMap.put(account4.getCardNumber(), account4);
        accountMap.put(account5.getCardNumber(), account5);
        accountMap.put(account6.getCardNumber(), account6);
        accountMap.put(account7.getCardNumber(), account7);
        accountMap.put(account8.getCardNumber(), account8);
        accountMap.put(account9.getCardNumber(), account9);
        accountMap.put(account10.getCardNumber(), account10);
        return  accountMap;
    }
    //@Autowired
    //TransactionService transactionService;

    @PostMapping
    public ResponseEntity<?> processTransaction(@RequestBody TransactionDTO transactionDTO) {
        Map<String, Accounts> accountMap = new HashMap<>();
        accountMap=createListOfCards();
        boolean cardExists = false;
        if(accountMap.containsKey(transactionDTO.getCardNumber())){
            System.out.println("This card is ours!");
            cardExists = true;
        }
        else{
            System.out.println("This card is not ours!");
        }
        if(cardExists){
            TransactionService transactionService=new TransactionService();
            System.out.println(transactionService);
        }
        //logger.info("Processing transaction: " + transactionDTO.getCardNumber() + " with amount: " + transactionDTO.getAmount());
        //System.out.println("Processing transaction: " + transactionDTO.getCardNumber() + " with amount: " + transactionDTO.getAmount());
        //return transactionService.processTransaction(transactionDTO);
        //this is a new comment
        //System.out.println(transactionDTO.getAmount()+" "+transactionDTO.getCardNumber());
        return null;
    }
}