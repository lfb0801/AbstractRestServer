package com.abstractrestapplication.rest.controller;

import com.abstractrestapplication.rest.hateaos.HateaosResponse;
import com.abstractrestapplication.rest.hateaos.HateaosUtil;
import handlers.CRUDService;
import lombok.Setter;
import models.PersistenceEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;

@RestController
public abstract class CRUDController<T extends PersistenceEntity, Identifier extends Serializable> {

    @Setter
    private CRUDService<T, Identifier> service;

    public abstract void initService();

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends CRUDController<T, Identifier>> getClazz();


    /**
     * Retrieve the options for this rest service.
     *
     * @return a header with allowed options.
     */
    @RequestMapping(value = "", method = RequestMethod.OPTIONS)
    public ResponseEntity<String> options() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("allow", "HEAD,GET,PUT,DELETE,POST,OPTIONS");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("/")
    public HttpEntity<String> create(@RequestParam final T entity) {
        service.create(entity);
        return new ResponseEntity<String>("Object created", HttpStatus.OK);
    }

    @GetMapping("/")
    public HttpEntity<HateaosResponse> get(@RequestParam final Identifier id) {
        final T result = service.read(id);
        return HateaosUtil.build(result);
    }

    @GetMapping("/")
    public HttpEntity<HateaosResponse> getAll() {
        return new ResponseEntity<>((HateaosResponse) service.readAll(), HttpStatus.OK);
    }

    @PutMapping("/")
    public HttpEntity<HateaosResponse> update(@RequestParam final T entity) {
        service.update(entity);
        return HateaosUtil.build(entity);
    }

    @DeleteMapping("/")
    public HttpEntity<HateaosResponse> delete(@PathVariable("id") final Identifier id) {
        service.delete(service.read(id));
        return HateaosUtil.build(id);
    }
}