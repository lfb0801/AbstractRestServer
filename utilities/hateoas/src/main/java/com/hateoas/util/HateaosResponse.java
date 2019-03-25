package com.hateoas.util;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.ResourceSupport;

public class HateaosResponse extends ResourceSupport {

    @Getter
    private final Object content;

    /**
     * Create a response with content.
     *
     * @param content the content to wrap.
     */
    @JsonCreator
    public HateaosResponse(final Object content) {
        this.content = content;
    }

    /**
     * Add arrays of links to this Hateoas object.
     *
     * @param links the arrays to add.
     */
    public void addAll(Link[]... links) {
        for (Link[] link : links) {
            this.add(link);
        }
    }
}
