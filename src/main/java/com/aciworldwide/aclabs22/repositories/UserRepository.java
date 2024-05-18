package com.aciworldwide.aclabs22.repositories;

import com.aciworldwide.aclabs22.entities.UserModel;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

import javax.persistence.LockModeType;
import java.util.UUID;

public interface UserRepository extends CrudRepository<UserModel, UUID> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    UserModel findUserModelByNameAndPassword(String name, String password);
}
