package com.abstractrestapplication.controllers;

import com.abstractrestapplication.models.User;
import com.abstractrestapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController extends HateaosController<User, Integer> {

    private UserService service;

    @Autowired
    public UserController(UserService service) {
        super(service);
        this.service = service;
    }

    @Override
    public Class<? extends HateaosController<User, Integer>> getClazz() {
        return this.getClass();
    }
}
