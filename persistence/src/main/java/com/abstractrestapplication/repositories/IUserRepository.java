package com.abstractrestapplication.repositories;

import com.abstractrestapplication.models.User;
import com.hateoas.persistence.IStandardRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends IStandardRepository<User, Integer> {
}
