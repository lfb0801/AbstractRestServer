package com.abtractrestapplication.logic;

import com.abtractrestapplication.domain.PersistenceEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.hateoas.ResourceSupport;

import java.util.Collection;
import java.util.Optional;

public abstract class HateoasService<T extends ResourceSupport, Identifier> {

    private PagingAndSortingRepository<T, Identifier> repo;

    public HateoasService(PagingAndSortingRepository<T, Identifier> _repo) {
        this.repo = _repo;
    }

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends HateoasService<T, Identifier>> getClazz();

    public void create(T entity) {
        repo.save(entity);
    }

    public T read(Identifier indentifier) {
        Optional<T> result = repo.findById(indentifier);
        return result.orElse(null);
    }

    public Collection<T> readAll() {
        Iterable<T> result = repo.findAll();
        return (Collection<T>) result;
    }

    public void update(T entity) {
        repo.save(entity);
    }

    public void delete(T entity) {
        repo.delete(entity);
    }
}
