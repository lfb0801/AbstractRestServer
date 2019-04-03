package com.abstractrestapplication.models;

import com.abtractrestapplication.domain.PersistenceEntity;

import javax.persistence.Entity;

@Entity
public class User extends PersistenceEntity<Integer> {

    private String firstName;
    private String lastName;
}
