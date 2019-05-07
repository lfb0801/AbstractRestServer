package com.abstractrestapplication.models;

import com.abstractrestapplication.interfaces.HateoasObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
public class User extends ResourceSupport implements HateoasObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Getter
    private String username;

    @Getter
    private String password;

    @Override
    @JsonIgnore
    public Serializable getIdentifier() {
        return this.id;
    }

    //  login
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
