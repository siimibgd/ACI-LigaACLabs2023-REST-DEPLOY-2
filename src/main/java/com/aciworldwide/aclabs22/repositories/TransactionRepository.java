package com.aciworldwide.aclabs22.repositories;

import com.aciworldwide.aclabs22.entities.TransactionModel;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.UUID;


public interface TransactionRepository extends CrudRepository<TransactionModel, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    TransactionModel findById(long id);

}
