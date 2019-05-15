package com.abstractrestapplication.service;

import com.abstractrestapplication.interfaces.HateoasObject;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.hateoas.ResourceSupport;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

// TODO: 14-5-2019 #2
public abstract class HateoasService<T extends ResourceSupport & HateoasObject, Identifier> {

    private PagingAndSortingRepository<T, Identifier> repository;

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends HateoasService<T, Identifier>> getClazz();

    public void create(T entity) {
        repository.save(entity);
    }

    public T read(Identifier indentifier) {
        Optional<T> result = repository.findById(indentifier);
        return result.orElse(null);
    }

    public Collection<T> readAll() {
        Iterable<T> result = repository.findAll();
        return (Collection<T>) result;
    }

    public void update(T entity) {
        try {
            Identifier id = (Identifier) entity.getIdentifier();
            T storable = repository.findById(id).get();
            Field[] fields = storable.getClass().getDeclaredFields();
            for (Field f : fields){
                f.setAccessible(true);
                f.set(storable, f.get(entity));
            }
            repository.save(storable);
        } catch (IllegalAccessException ex){
            System.out.println(ex);
        }
    }
    public void delete(T entity) {
        repository.delete(entity);
    }
}
