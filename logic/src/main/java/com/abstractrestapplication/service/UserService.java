package com.abstractrestapplication.service;

import com.hateoas.logic.HateaosService;
import com.abstractrestapplication.models.User;
import lombok.Setter;
import org.springframework.stereotype.Service;
import com.abstractrestapplication.repositories.IUserRepository;

@Service
public class UserService extends HateaosService<User, Integer> {

    @Setter
    private IUserRepository repository;

    @Override
    public void initRepository() {
        setRepository(repository);
    }

    @Override
    public Class<? extends HateaosService<User, Integer>> getClazz() {
        return this.getClass();
    }
}
