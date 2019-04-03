package com.abtractrestapplication.util;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class HateoasUtil {

    /**
     * Build an HttpEntity with a proper Hateoas response body.
     *
     * @param object the object to add as content.
     * @return a abtractrestapplication response.
     */
    public static HttpEntity<HateoasResponse> build(final Object object){
        return new ResponseEntity<HateoasResponse>(new HateoasResponse(object), HttpStatus.OK);
    }

    /**
     * Create a Response with links.
     *
     * @param object the content.
     * @param links  the links.
     * @return a Response around the object.
     */
    public static HateoasResponse toHateoas(final Object object, final Link[]... links){
        HateoasResponse hateoasResponse = toHateoas(object);
        hateoasResponse.addAll(links);
        return hateoasResponse;
    }

    /**
     * Create a Response.
     *
     * @param object the content.
     * @return a Response around the content.
     */
    public static HateoasResponse toHateoas(final Object object) {
        return new HateoasResponse(object);
    }

}
