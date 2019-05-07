package com.abstractrestapplication.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Wrapper for multiple {@linkplain WrapWithLink} annotations.
 *
 * @author peaseloxes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface WrapWithLinks {
    /**
     * Add multiple {@linkplain WrapWithLink} annotations.
     *
     * @return the array of WrapWithLink annotations.
     */
    WrapWithLink[] links();
}
