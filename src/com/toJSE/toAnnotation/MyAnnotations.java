package com.toJSE.toAnnotation;

        import java.lang.annotation.ElementType;
        import java.lang.annotation.Target;

@Target({ElementType.TYPE_PARAMETER,ElementType.TYPE_USE,ElementType.TYPE,ElementType.FIELD,ElementType.METHOD
        ,ElementType.CONSTRUCTOR,ElementType.LOCAL_VARIABLE,ElementType.ANNOTATION_TYPE,ElementType.PARAMETER})
public @interface MyAnnotations {
    MyAnnotation[] value();
}
