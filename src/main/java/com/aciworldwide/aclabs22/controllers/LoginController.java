package com.aciworldwide.aclabs22.controllers;

import com.aciworldwide.aclabs22.entities.UserModel;
import com.aciworldwide.aclabs22.services.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserModel loginUser(@RequestBody() UserModel userModel) {
        System.out.println(userModel);
        return userService.loginUser(userModel);
    }
}