package com.toJSE.toAnnotation.bean;

import com.toJSE.toAnnotation.MyAnnotation;
import com.toJSE.toAnnotation.MyAnnotations;

public class Person {
    // 注解使用
    @MyAnnotation(value = "姓名")
    String name;
    @MyAnnotation("年龄")
    int age;
    @MyAnnotation("职业")
    String job;

    @MyAnnotation(value = "构造函数")
    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }
    // 重复注解使用
    // 方式1.使用注解数组，这个数组也是一个注解
    @MyAnnotations({@MyAnnotation("跑步1"),@MyAnnotation("跑步2")})
    public void run(){}
    // 方式2.使用 Repeatable 元注解修饰的注解
    @MyAnnotation("跑步3")
    @MyAnnotation("跑步4")
    public void climb(){}
}
