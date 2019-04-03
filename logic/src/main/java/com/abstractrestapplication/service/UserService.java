package com.abstractrestapplication.service;

import com.abstractrestapplication.models.User;
import com.abtractrestapplication.logic.HateoasService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abstractrestapplication.repositories.IUserRepository;

@Service
public class UserService extends HateoasService<User, Integer> {

    @Setter
    private final IUserRepository repository;

    @Autowired
    public UserService(IUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Class<? extends HateoasService<User, Integer>> getClazz() {
        return this.getClass();
    }
}
