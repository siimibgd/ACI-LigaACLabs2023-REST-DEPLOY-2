package com.aciworldwide.aclabs22.sharedData;

import com.aciworldwide.aclabs22.dto.TransactionDTO;
import lombok.Getter;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InProcessQueue {
    @Getter
    private static InProcessQueue inProcessQueue = new InProcessQueue();
    @Getter
    private BlockingQueue<TransactionDTO> accountQueue=new ArrayBlockingQueue<>(10);
    public void addToQueue(TransactionDTO transactionDTO) {
        accountQueue.add(transactionDTO);
    }

}
