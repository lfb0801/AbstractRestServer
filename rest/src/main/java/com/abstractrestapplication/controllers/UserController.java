package com.abstractrestapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.abstractrestapplication.service.UserService;
import com.abstractrestapplication.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hateoas.rest.HateaosController;

import javax.annotation.PostConstruct;

@RestController
@RequestMapping("/users")
public class UserController extends HateaosController<User, Integer> {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Override
    @PostConstruct
    public void initService(){
        setService(service);
    }

    @Override
    public Class<? extends HateaosController<User, Integer>> getClazz(){ return this.getClass(); }
}