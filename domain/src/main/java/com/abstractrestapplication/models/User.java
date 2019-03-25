package com.abstractrestapplication.models;

import com.hateoas.domain.PersistenceEntity;
import lombok.Getter;

import javax.persistence.Entity;

@Entity
@Getter
public class User extends PersistenceEntity<Integer> {

    private String firstName;
    private String lastName;
}
