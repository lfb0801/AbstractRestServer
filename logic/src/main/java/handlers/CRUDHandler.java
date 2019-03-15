package handlers;

import com.j256.ormlite.dao.Dao;
import repositories.CRUDRepo;

import java.io.Serializable;
import java.util.List;

public abstract class CRUDHandler<T, Identifier extends Serializable, repo extends CRUDRepo>{

    public void create(T entity) {
    }

    public T read(Identifier indentifier) throws InstantiationException, IllegalAccessException {
    }

    public List readAll() throws InstantiationException, IllegalAccessException {
        return null;
    }

    public void update(T entity) {

    }

    public void deleteById(Identifier identifier) {

    }
}
