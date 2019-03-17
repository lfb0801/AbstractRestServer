package models;

import lombok.Getter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@MappedSuperclass
public abstract class PersistenceEntity<Identifier extends Serializable> implements Serializable {

    @Id
    @GeneratedValue
    private Identifier id;
}
