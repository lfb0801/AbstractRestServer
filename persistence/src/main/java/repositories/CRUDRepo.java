package repositories;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public abstract class CRUDRepo<T, Identifier> extends Dao<T, Identifier> {
    public CRUDRepo(){

    }
}
