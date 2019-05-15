package com.abstractrestapplication.controllers;

import com.abstractrestapplication.annotations.LoginRequired;
import com.abstractrestapplication.annotations.WrapWithLink;
import com.abstractrestapplication.interfaces.HateoasObject;
import com.abstractrestapplication.models.HateoasResponse;
import com.abstractrestapplication.service.HateoasService;
import com.abstractrestapplication.utils.HateoasUtil;
import org.assertj.core.util.Lists;
import org.springframework.hateoas.ResourceSupport;
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

// TODO: 15-5-2019 #2 
public abstract class HateaosController<T extends ResourceSupport & HateoasObject, Identifier extends Serializable> {

    private HateoasService<T, Identifier> service;

    public HateaosController(HateoasService<T, Identifier> _service) {
        this.service = _service;
    }

    /**
     * Use this method to return the classname of the instance.
     *
     * @return class of the instance.
     */
    public abstract Class<? extends HateaosController<T, Identifier>> getClazz();

    /**
     * Retrieve the options for this rest services.
     *
     * @return a header with allowed options.
     */
    @RequestMapping(value = "/", method = RequestMethod.OPTIONS)
    @LoginRequired(false)
    public HttpEntity<String> options() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
        map.add("allow", "HEAD,GET,PUT,DELETE,POST,OPTIONS");
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping(value = "/")
    @WrapWithLink
    public HttpEntity<HateoasResponse> create(final HttpServletRequest request, @RequestBody T entity) {
        service.create(entity);
        HateoasResponse response = HateoasUtil.toHateoas(
                entity,
                WrapWithLink.Type.SELF.link(request, "/" + entity.getId()),
                WrapWithLink.Type.POST.link(request, ""),
                WrapWithLink.Type.UPDATE.link(request, "/" + entity.getId()),
                WrapWithLink.Type.DELETE.link(request, "/" + entity.getId())
        );
        return HateoasUtil.build(response);
    }

    @GetMapping(value = "/")
    @WrapWithLink
    @LoginRequired
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
        return HateoasUtil.build(responses);
    }

    @GetMapping(value = "/{id}")
    @WrapWithLink
    public HttpEntity<HateoasResponse> get(final HttpServletRequest request, @PathVariable Identifier id) {
        final T entity = service.read(id);

        HateoasResponse response = HateoasUtil.toHateoas(
                entity,
                WrapWithLink.Type.SELF.link(request, "/" + entity.getId()),
                WrapWithLink.Type.POST.link(request, ""),
                WrapWithLink.Type.UPDATE.link(request, "/" + entity.getId()),
                WrapWithLink.Type.DELETE.link(request, "/" + entity.getId())
        );
        return HateoasUtil.build(response);
    }

    @PutMapping(value = "/")
    @WrapWithLink
    public HttpEntity<HateoasResponse> update(@RequestBody T entity) {
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