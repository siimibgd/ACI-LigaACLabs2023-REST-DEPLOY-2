package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.sharedData.AccountsDB;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class AccountService implements Runnable{
    ConcurrentHashMap<String, Accounts> accountMap= AccountsDB.getInstance().getAccountsMap();
    Logger logger = Logger.getLogger("AccountServiceLogger");
    TransactionDTO transactionDTO;
    public AccountService(TransactionDTO transactionDTO) {
        this.transactionDTO = transactionDTO;
    }
    public ResponseEntity<?> getAccount() {
        return null;
    }

    public ResponseEntity<?> addAccount() {
        return null;
    }

    public ResponseEntity<?> updateAccount() {
        return null;
    }

    public ResponseEntity<?> deleteAccount() {
        return null;
    }
    public ResponseEntity<?> validateAccount() {
        return null;
    }
    @Override
    public void run() {
        try {
            String cardNumber=transactionDTO.getCardNumber();
            if(accountQueue.size()<10){
                System.out.println(accountQueue.size() +" "+ cardNumber);
                if(accountMap.containsKey(cardNumber)){
                    System.out.println("My account!");
                    accountQueue.put(transactionDTO);
                    notify();
                }
                else {
                    notify();
                    System.out.println("Not our account!");

                }
            }else{
                notify();
                System.out.println("Account Queue is full");
            }
        }
        catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
