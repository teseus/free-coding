package com.teseus.annotations.simplest;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface MySampleAnnotation {
    public String name();
    public int age();
}
