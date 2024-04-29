package com.aciworldwide.aclabs22.repositories;

import com.aciworldwide.aclabs22.entities.AccountModel;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface AccountRepository extends CrudRepository<AccountModel, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    AccountModel findByCardNumber(String cardNumber);
}
