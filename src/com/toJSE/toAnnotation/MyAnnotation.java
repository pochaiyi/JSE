package com.toJSE.toAnnotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;

/**
 * 自定义注解
 * 注：Repeatable元注解参数中的注解的生命周期与可修饰类型必须与被修饰注解一致
 */
// 可重复注解
@Repeatable(MyAnnotations.class)
// 类型注解
@Target({ElementType.TYPE_PARAMETER,ElementType.TYPE_USE,ElementType.TYPE,ElementType.FIELD,ElementType.METHOD
        ,ElementType.CONSTRUCTOR,ElementType.LOCAL_VARIABLE,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
public @interface MyAnnotation {
    String value() default "Hello";
}
