package com.abstractrestapplication.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Integer id;

    @Getter
    private String token;

    @JsonIgnore
    @Getter
    @Setter
    private Timestamp timestamp;

    @JsonIgnore
    @Getter
    @Setter
    private Integer userId;

    public Token(@JsonProperty("token") String token) {
        this.token = token;
    }
}
