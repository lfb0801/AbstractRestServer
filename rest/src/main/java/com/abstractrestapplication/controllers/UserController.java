package com.abstractrestapplication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import com.abstractrestapplication.service.UserService;
import com.abstractrestapplication.models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abtractrestapplication.rest.HateoasController;

@RestController
@RequestMapping("/users")
public class UserController extends HateoasController<User, Integer> {

    private UserService service;

    public UserController() {
        //  needed for autowiring
    }

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }


    @Override
    public Class<? extends HateoasController<User, Integer>> getClazz(){ return this.getClass(); }
}