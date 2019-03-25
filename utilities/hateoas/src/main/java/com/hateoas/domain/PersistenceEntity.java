package com.hateoas.domain;

import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Getter
@Entity
public abstract class PersistenceEntity<Identifier extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private Identifier id;
}
