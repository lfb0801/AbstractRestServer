package com.hateoas.util;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class HateaosUtil {

    /**
     * Build an HttpEntity with a proper Hateoas response body.
     *
     * @param object the object to add as content.
     * @return a hateoas response.
     */
    public static HttpEntity<HateaosResponse> build(final Object object){
        return new ResponseEntity<HateaosResponse>(new HateaosResponse(object), HttpStatus.OK);
    }

    /**
     * Create a Response with links.
     *
     * @param object the content.
     * @param links  the links.
     * @return a Response around the object.
     */
    public static HateaosResponse toHateoas(final Object object, final Link[]... links){
        HateaosResponse hateaosResponse = toHateoas(object);
        hateaosResponse.addAll(links);
        return hateaosResponse;
    }

    /**
     * Create a Response.
     *
     * @param object the content.
     * @return a Response around the content.
     */
    public static HateaosResponse toHateoas(final Object object) {
        return new HateaosResponse(object);
    }

}
