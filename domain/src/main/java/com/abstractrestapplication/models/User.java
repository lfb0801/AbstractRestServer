package com.abstractrestapplication.models;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User extends ResourceSupport {

    @Id
    @GeneratedValue
    private Integer id;

    private String firstName;
    private String lastName;

    public Link getId() {
        return new Link(id.toString());
    }
}
