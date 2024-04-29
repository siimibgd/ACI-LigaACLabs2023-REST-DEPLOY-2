package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import com.aciworldwide.aclabs22.entities.Accounts;
import com.aciworldwide.aclabs22.entities.Transactions;
import com.aciworldwide.aclabs22.sharedData.AccountsDB;
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

public class TransactionService implements Runnable{


    ConcurrentHashMap<String, Accounts> accountMap= AccountsDB.getInstance().getAccountsMap();
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
    private static short whyDeclined(boolean enoughAmount, boolean inDailySumRange, boolean inTransactionRange){
        if(!enoughAmount)
            return 1;
        if(!inTransactionRange)
            return 2;
        if(!inDailySumRange)
            return 3;
        return -1;
    }
    class ProducerConsumer {
        //private TransactionDTO transactionDTO;
        //private BlockingQueue<TransactionDTO> accountQueue=new ArrayBlockingQueue<>(10);

        public ProducerConsumer(TransactionDTO transactionDTO){
            this.transactionDTO=transactionDTO;
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
