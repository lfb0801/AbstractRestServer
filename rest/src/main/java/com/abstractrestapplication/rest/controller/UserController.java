package com.abstractrestapplication.rest.controller;

import handlers.UserHandler;
import models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends CRUDController<User, Integer>{
    public UserController() {
        super(new UserHandler());
    }
}