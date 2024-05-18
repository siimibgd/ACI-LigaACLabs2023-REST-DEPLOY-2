package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.dto.AccountDTO;
import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.AccountModel;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.repositories.AccountRepository;
import com.aciworldwide.aclabs22.sharedData.AccountsDB;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Transactional
@Service
public class AccountService{

    @Autowired
    AccountRepository accountRepository;

    private boolean insertAccount(AccountDTO accountDTO){
        if(accountRepository.findByCardNumber(accountDTO.cardNumber())==null){
            return accountDTO!=null && accountDTO.cardNumber()!=null;
        }
        return false;
    }
    public ResponseEntity<?> getAccount(String cardNumber) {
        AccountModel accountModel = accountRepository.findByCardNumber(cardNumber);
        if(accountModel==null){
            AccountDTO response = new AccountDTO();
            response.setStatus("Account Not Found");
            return new ResponseEntity<>(response,new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(new AccountDTO(accountModel), new HttpHeaders(), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> addAccount(AccountDTO accountDTO) {
        if(insertAccount(accountDTO)){
            AccountDTO response = new AccountDTO(accountRepository.save(new AccountModel(accountDTO)));
        }else{
            AccountDTO response = new AccountDTO();
            response.setStatus("Account Exists");
            return new ResponseEntity<>(response,new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }

    public ResponseEntity<?> updateAccount(AccountDTO accountDTO) {
        return null;
    }

    public ResponseEntity<?> deleteAccount(AccountDTO accountDTO) {
        return null;
    }
    public ResponseEntity<?> validateAccount() {
        return null;
    }

    public ResponseEntity<?> getAllAccounts() {
        Iterable<AccountModel> accounts = accountRepository.findAll();
        return new ResponseEntity<>(accounts,new HttpHeaders(), HttpStatus.OK);
        }
}
