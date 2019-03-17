package handlers;

import interfaces.IRestRepository;
import lombok.Setter;
import models.PersistenceEntity;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class CRUDService<T extends PersistenceEntity, Identifier> {

    @Setter
    private IRestRepository repo;

    /**
     * Use this method to set the repository.
     */
    public abstract void initRepository();

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends CRUDService<T, Identifier>> getClazz();

    public void create(T entity) {
        try {
            repo.create(entity);
        } catch (SQLException exception) {

        }
    }

    public T read(Identifier indentifier) {
        try {
            return (T) repo.queryForId(indentifier);
        } catch (SQLException exception) {

        }
        return null;
    }

    public Collection<T> readAll() {
        try {
            return (List<T>) repo.queryForAll();
        } catch (SQLException exception) {

        }
        return null;
    }

    public void update(T entity) {
        try {
            repo.update(entity);
        } catch (SQLException exception) {

        }
    }

    public void delete(T entity) {
        try {
            repo.delete(entity);
        } catch (SQLException exception) {

        }
    }
}
