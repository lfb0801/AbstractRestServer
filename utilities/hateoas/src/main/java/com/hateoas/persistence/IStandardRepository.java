package com.hateoas.persistence;

import com.j256.ormlite.dao.Dao;
import com.hateoas.domain.PersistenceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IStandardRepository<T extends PersistenceEntity, Identifier> extends Dao<T, Identifier> {
}