package com.abstractrestapplication.rest.controller;

import handlers.UserService;
import lombok.Setter;
import models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends CRUDController<User, Integer> {

    @Setter
    private UserService service;

    @Override
    public void initService(){
    }

    @Override
    public Class<? extends CRUDController<User, Integer>> getClazz(){ return this.getClass(); }
}