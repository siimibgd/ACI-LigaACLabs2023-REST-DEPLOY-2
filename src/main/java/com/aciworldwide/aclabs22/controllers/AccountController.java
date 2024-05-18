package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.dto.AccountDTO;
import com.aciworldwide.aclabs22.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @Autowired
    AccountService accountService;

    //request
    @GetMapping(path="/{cardNumber}")
    public ResponseEntity<?> getAccount(String cardNumber) {
        return accountService.getAccount(cardNumber);
    }
    @GetMapping
    public ResponseEntity<?> getAllAccounts() {
        return accountService.getAllAccounts();
    }
    // create
    @PostMapping
    public ResponseEntity<?> addAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.addAccount(accountDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.updateAccount(accountDTO);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAccount(@RequestBody AccountDTO accountDTO) {
        return accountService.deleteAccount(accountDTO);
    }
}
