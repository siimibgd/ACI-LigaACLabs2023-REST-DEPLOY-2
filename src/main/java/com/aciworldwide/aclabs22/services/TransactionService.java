package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.AccountModel;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.entities.TransactionModel;
import com.aciworldwide.aclabs22.entities.Transactions;
import com.aciworldwide.aclabs22.repositories.AccountRepository;
import com.aciworldwide.aclabs22.repositories.TransactionRepository;
import com.aciworldwide.aclabs22.sharedData.AccountsDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

@Transactional
@Service
public class TransactionService{

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    AccountRepository accountRepository;

    private String transactionValidation(TransactionDTO transactionDTO,AccountModel accounts){
        if(accounts==null || transactionDTO==null){
            return "Declined";
        }
        if(transactionDTO.getAmount()>accounts.getAmount()){
            return "Declined";
        }
        if(transactionDTO.getAmount()<=accounts.getAmount()){
            if(transactionDTO.getAmount()+accounts.getDailyTxSum()<accounts.getDailyTxSumLimit()){
                if(accounts.getDailyTxLimit()>accounts.getDailyTx()){
                    return "Approved";
                }else {
                    return "Declined";
                }
            }else{
                return "Declined";
            }
        }
        return "Approved";
    }

    private void updateAccount(AccountModel account, Double amount) {
        account.setAmount(account.getAmount() - amount);

        if (account.getDailyTxSumLimit() != -1) {
            account.setDailyTxSum(amount);
        }

        if (account.getDailyTxLimit() != -1) {
            account.setDailyTx();
        }
    }

    @Transactional
    public ResponseEntity<?> processTransaction(TransactionDTO transactionDTO) {
        AccountModel accountModel = accountRepository.findByCardNumber(transactionDTO.getCardNumber());
        TransactionModel transactionModel= new TransactionModel(transactionDTO);
        transactionModel.setReturnCode(transactionValidation(transactionDTO,accountModel));
        transactionRepository.save(transactionModel);
        if (transactionModel.getReturnCode().equals("Approved")) {
            updateAccount(accountModel, transactionModel.getAmount());
            accountRepository.save(accountModel);
        }
        return null;
    }


    public ResponseEntity<?> getAllTransactions() {
        Iterable<TransactionModel> transactions = transactionRepository.findAll();
        return new ResponseEntity<>(transactions,new HttpHeaders(), HttpStatus.OK);
    }
}
