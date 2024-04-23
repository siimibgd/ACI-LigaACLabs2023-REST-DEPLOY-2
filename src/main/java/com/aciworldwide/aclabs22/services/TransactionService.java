package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.entities.Transactions;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class TransactionService {

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
    ConcurrentHashMap<String, Accounts> accountMap=createListOfCards();
    Logger logger = Logger.getLogger("TransactionServiceLogger");
    FileHandler fileHandler;

    {
        try {
            fileHandler = new FileHandler("src/main/resources/transactionProcessingLog.log",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Lock lock = new ReentrantLock();

    private static void logMessage(Logger logger, String message){
        lock.lock();
        try {
            logger.info(message);
        } finally {
            lock.unlock();
        }
    }
    class ProducerConsumer {
        private TransactionDTO transactionDTO;
        private BlockingQueue<TransactionDTO> accountQueue=new ArrayBlockingQueue<>(10);
        public void start(){
            Thread producerThread = new Thread(new ProducerConsumer.taskProducer());
            Thread consumerThread = new Thread(new ProducerConsumer.taskConsumer());

            producerThread.start();
            consumerThread.start();
        }
        public ProducerConsumer(TransactionDTO transactionDTO){
            this.transactionDTO=transactionDTO;
        }
        private static short whyDeclined(boolean enoughAmount, boolean inDailySumRange, boolean inTransactionRange){
            if(!enoughAmount)
                return 1;
            if(!inTransactionRange)
                return 2;
            if(!inDailySumRange)
                return 3;
            return -1;
        }
        class taskProducer implements Runnable{
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
        class taskConsumer implements Runnable{
            @Override
            public void run() {
                try {
                    if(!accountQueue.isEmpty()){
                        TransactionDTO transactionDTOConsumer = accountQueue.take();
                        Accounts accounts = accountMap.get(transactionDTOConsumer.getCardNumber());
                        if(accounts!=null){
                            double askedAmount=transactionDTOConsumer.getAmount();
                            double inAccountAmount=accounts.amount();

                            double todaySpentAmount=accounts.dailyTxSum();
                            double maximumDailyAmount=accounts.dailyTxSumLimit();

                            double todayTransactionNumber=accounts.dailyTx();
                            double maximumDailyTransactions=accounts.dailyTxLimit();

                            boolean enoughAmount = askedAmount<inAccountAmount;
                            boolean inDailySumRange= maximumDailyAmount==1 || (todaySpentAmount+askedAmount)<maximumDailyAmount;
                            boolean inTransactionRange= maximumDailyTransactions==-1 || todayTransactionNumber<maximumDailyTransactions;

                            if(enoughAmount && inDailySumRange && inTransactionRange){
                                    logMessage(logger,"Approved!");
                                    accounts.setDailyTx();
                                    accounts.setDailyTxSum(transactionDTOConsumer.getAmount());
                                    accounts.setAmount(transactionDTOConsumer.getAmount());
                                    accountMap.put(accounts.cardNumber(),accounts);
                            }else{
                                switch (whyDeclined(enoughAmount,inDailySumRange,inTransactionRange)){
                                    case 1:
                                        logMessage(logger,"Declined - insufficient funds!");
                                        break;
                                    case 2:
                                        logMessage(logger,"Declined - transaction number limit reached!");
                                        break;
                                    case 3:
                                        logMessage(logger,"Declined - transaction amount limit reached!");
                                        break;
                                }
                            }

                        }
                        /*
                        if(accounts!=null){
                            double askedAmount=transactionDTOConsumer.getAmount();
                            if(askedAmount<=accounts.amount()){
                                System.out.println("This account has set :"+accounts.dailyTxLimit());
                                if(accounts.dailyTxLimit()==-1 || accounts.dailyTx()<accounts.dailyTxLimit()){
                                    if(accounts.dailyTxSumLimit()==-1 || accounts.dailyTxSum()+transactionDTOConsumer.getAmount()<=accounts.dailyTxSumLimit()){
                                        accounts.setDailyTxSum(transactionDTOConsumer.getAmount());
                                        accounts.setDailyTx();
                                        accounts.setAmount(transactionDTOConsumer.getAmount());
                                    }
                                    else {
                                        System.out.println("Transaction daily amount limit reached");
                                    }
                                }else {
                                    System.out.println("Transaction daily limit reached");
                                }
                            }else{
                                System.out.println("Not enough money in your account!");
                            }
                        }else{
                            System.out.println("Error while processing valid transaction!");
                        }*/
                    }else {
                        notifyAll();
                        wait();
                    }
                }catch (Exception e) {
                    Thread.currentThread().interrupt();
                }

            }
        }
    }
    public ResponseEntity<?> processTransaction(TransactionDTO transactionDTO) {
        logger.addHandler(fileHandler);
        fileHandler.setFormatter(new SimpleFormatter());
        ProducerConsumer producerConsumer = new ProducerConsumer(transactionDTO);
        producerConsumer.start();
        fileHandler.close();
        return null;
    }
}
