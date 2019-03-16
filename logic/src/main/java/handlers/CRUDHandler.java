package handlers;

import com.j256.ormlite.dao.Dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

public abstract class CRUDHandler<T, Identifier extends Serializable, Repo extends Dao> {

    private Repo repo;

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
