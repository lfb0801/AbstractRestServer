package com.abtractrestapplication.rest;

import com.abtractrestapplication.logic.HateoasService;
import com.abtractrestapplication.domain.PersistenceEntity;
import com.abtractrestapplication.util.HateoasResponse;
import com.abtractrestapplication.util.HateoasUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class HateoasController<T extends PersistenceEntity, Identifier extends Serializable> {

    private HateoasService<T, Identifier> service;

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends HateoasController<T, Identifier>> getClazz();


    /**
     * Retrieve the options for this rest services.
     *
     * @return a header with allowed options.
     */
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    public HttpEntity<String> options() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("allow", "HEAD,GET,PUT,DELETE,POST,OPTIONS");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = "/{entity}")
    @WrapWithLink
    public HttpEntity<HateoasResponse> create(@PathVariable T entity) {
        service.create(entity);
        return HateoasUtil.build(entity);
    }

    @GetMapping(value = "/")
    @WrapWithLink
    public HttpEntity<HateoasResponse> getAll(final HttpServletRequest request) {
        final List<T> entities = Lists.newArrayList(service.readAll());
        final List<HateoasResponse> responses = new ArrayList<>(entities.size());
        responses.addAll(entities.stream().map(entity -> HateoasUtil.toHateoas(
                entity,
                WrapWithLink.Type.SELF.link(request, "/" + entity.getId()),
                WrapWithLink.Type.POST.link(request, ""),
                WrapWithLink.Type.UPDATE.link(request, "/" + entity.getId()),
                WrapWithLink.Type.DELETE.link(request, "/" + entity.getId())
        )).collect(Collectors.toList()));
        return HateoasUtil.build(responses);    }

    @GetMapping(value = "/{id}")
    @WrapWithLink
    public HttpEntity<HateoasResponse> get(@PathVariable Identifier id) {
        final T result = service.read(id);
        return HateoasUtil.build(result);
    }

    @PutMapping(value = "/{entity}")
    @WrapWithLink
    public HttpEntity<HateoasResponse> update(@PathVariable T entity) {
        service.update(entity);
        return HateoasUtil.build(entity);
    }

    @DeleteMapping(value = "/{id}")
    @WrapWithLink
    public HttpEntity<HateoasResponse> delete(@PathVariable("id") Identifier id) {
        service.delete(service.read(id));
        return HateoasUtil.build(id);
    }

}