package com.abstractrestapplication.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author peaseloxes
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface LoginRequired {
    /**
     * Whether or not login is required, optional.
     *
     * @return true if required, false otherwise.
     */
    boolean value() default true;
}
