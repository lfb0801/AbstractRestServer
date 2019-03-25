package com.hateoas.rest;

import com.hateoas.logic.HateaosService;
import lombok.Setter;
import com.hateoas.domain.PersistenceEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import com.hateoas.util.HateaosResponse;
import com.hateoas.util.HateaosUtil;

import java.io.Serializable;

@CrossOrigin
@RestController
public abstract class HateaosController<T extends PersistenceEntity, Identifier extends Serializable> {

    @Setter
    private HateaosService<T, Identifier> service;

    public abstract void initService();

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends HateaosController<T, Identifier>> getClazz();


    /**
     * Retrieve the options for this rest service.
     *
     * @return a header with allowed options.
     */
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public HttpEntity<String> options() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("allow", "HEAD,GET,PUT,DELETE,POST,OPTIONS");
        return new ResponseEntity<String>(map, HttpStatus.OK);
    }

    @PostMapping(value = "{entity}")
    public HttpEntity<HateaosResponse> create(@PathVariable final T entity) {
        service.create(entity);
        return HateaosUtil.build(entity);
    }

    @GetMapping(value = "{id}")
    public HttpEntity<HateaosResponse> get(@PathVariable final Identifier id) {
        final T result = service.read(id);
        return HateaosUtil.build(result);
    }

    @GetMapping(value = "")
    public HttpEntity<HateaosResponse> getAll() {
        return HateaosUtil.build(service.readAll());
    }

    @PutMapping(value = "{entity}")
    public HttpEntity<HateaosResponse> update(@PathVariable final T entity) {
        service.update(entity);
        return HateaosUtil.build(entity);
    }

    @DeleteMapping(value = "{id}")
    public HttpEntity<HateaosResponse> delete(@PathVariable("id") final Identifier id) {
        service.delete(service.read(id));
        return HateaosUtil.build(id);
    }
}