package com.toJSE.toReflection.bean;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface An1 {
    String value() default "An1";
}
