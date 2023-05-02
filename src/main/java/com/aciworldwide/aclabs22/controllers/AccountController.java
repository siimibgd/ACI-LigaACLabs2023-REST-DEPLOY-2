package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public class AccountController {

    @Autowired
    AccountService accountService;

    public ResponseEntity<?> getAccount() {
        return accountService.getAccount();
    }

    public ResponseEntity<?> addAccount() {
        return accountService.addAccount();
    }

    public ResponseEntity<?> updateAccount() {
        return accountService.updateAccount();
    }

    public ResponseEntity<?> deleteAccount() {
        return accountService.deleteAccount();
    }
}
