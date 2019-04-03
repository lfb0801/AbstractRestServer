package com.abstractrestapplication.repositories;

import com.abstractrestapplication.models.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends PagingAndSortingRepository<User, Integer> {
}
