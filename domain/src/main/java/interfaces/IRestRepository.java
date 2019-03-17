package interfaces;

import com.j256.ormlite.dao.Dao;
import models.PersistenceEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface IRestRepository <T extends PersistenceEntity, Identifier> extends Dao<T, Identifier> {
}