package com.aciworldwide.aclabs22.services;

import com.aciworldwide.aclabs22.entities.UserModel;
import com.aciworldwide.aclabs22.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public UserModel loginUser(UserModel userModel) {
        System.out.println(userModel.getName()+" "+userModel.getPassword());
        return userRepository.findUserModelByNameAndPassword(userModel.getName(), userModel.getPassword());
    }
}