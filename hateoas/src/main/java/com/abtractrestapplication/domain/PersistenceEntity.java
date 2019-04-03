package com.abtractrestapplication.domain;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public abstract class PersistenceEntity<Identifier extends Serializable> extends ResourceSupport {

    @Id
    @GeneratedValue
    private Identifier id;

    public Link getId() {
        return new Link(id.toString());
    }

}
