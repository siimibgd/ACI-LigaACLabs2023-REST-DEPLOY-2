package com.aciworldwide.aclabs22.sharedData;

import com.aciworldwide.aclabs22.entities.Accounts;
import lombok.Getter;

import java.util.concurrent.ConcurrentHashMap;


public class AccountsDB {
    @Getter
    private static AccountsDB instance = new AccountsDB();
    @Getter
    private ConcurrentHashMap<String, Accounts> accountsMap;
    private AccountsDB() {
        accountsMap = createListOfCards();
    }
    private static ConcurrentHashMap<String, Accounts> createListOfCards(){
        ConcurrentHashMap<String, Accounts> accountMap = new ConcurrentHashMap<>();

        Accounts account1 = new Accounts(1, "9000111100001121", 5000.0,5, -1, 0, 0);
        Accounts account2 = new Accounts(2, "1111222233334444", 55000.0, 6, 250, 0, 0);
        Accounts account3 = new Accounts(3, "4321000000056789", 6050.0, 7, 300, 0, 0);
        Accounts account4 = new Accounts(4, "4444000000000001", 6505.0, -1, 400, 0, 0);
        Accounts account5 = new Accounts(5, "9000000000000000", 7300.0, 9, 250, 0, 0);
        Accounts account6 = new Accounts(6, "9218740563280417", 7580.0, 10, 250, 0, 0);
        Accounts account7 = new Accounts(7, "5489371026750348", 85400.0, 11, 300, 0, 0);
        Accounts account8 = new Accounts(8, "3095871620497532", 8560.0, 12, 250, 0, 0);
        Accounts account9 = new Accounts(9, "8267591432085761", 90630.0, 13, 250, 0, 0);
        Accounts account10 = new Accounts(10, "1234000000056789", 12950.0, 14, 8000, 0, 0);

        accountMap.put(account1.cardNumber(), account1);
        accountMap.put(account2.cardNumber(), account2);
        accountMap.put(account3.cardNumber(), account3);
        accountMap.put(account4.cardNumber(), account4);
        accountMap.put(account5.cardNumber(), account5);
        accountMap.put(account6.cardNumber(), account6);
        accountMap.put(account7.cardNumber(), account7);
        accountMap.put(account8.cardNumber(), account8);
        accountMap.put(account9.cardNumber(), account9);
        accountMap.put(account10.cardNumber(), account10);
        return  accountMap;
    }



}
