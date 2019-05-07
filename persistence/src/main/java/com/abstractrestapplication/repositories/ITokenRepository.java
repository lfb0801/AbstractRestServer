package com.abstractrestapplication.repositories;

import com.abstractrestapplication.models.Token;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITokenRepository extends PagingAndSortingRepository<Token, Integer> {
    Token findByToken(String token);
}
