package com.toJSE.toReflection;

import com.toJSE.toReflection.bean.Person;
import org.junit.Test;

/**
 * 获取 Class实例有4种方式，常用 Class.forname()
 */
public class Class_instance_get {

    // 1.通过类获取
    @Test
    public void getInstance_1(){
        Class<Person> clazz1 = Person.class;
        System.out.println(clazz1); //class com.toJSE.toReflection.util4Reflection.Person

    }

    // 2.通过对象获取
    @Test
    public void getInstance_2(){
        Person lzd = new Person();
        Class<? extends Person> clazz2 = lzd.getClass();
        System.out.println(clazz2); //class com.toJSE.toReflection.util4Reflection.Person
    }

    // 3.通过类名称获取,最常用
    @Test
    public void getInstance_3() throws ClassNotFoundException {
        Class<?> clazz3 = Class.forName("com.toJSE.toReflection.bean.Person");
        System.out.println(clazz3); //class com.toJSE.toReflection.util4Reflection.Person
    }

    // 4.通过类加载器获取(了解)
    @Test
    public void getInstance_4() throws ClassNotFoundException {
        ClassLoader clazzLoader = Person.class.getClassLoader();
        Class<?> clazz4 = clazzLoader.loadClass("com.toJSE.toReflection.bean.Person");
        System.out.println(clazzLoader); //sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(clazz4); //class com.toJSE.toReflection.util4Reflection.Person
    }

    // 四种方式获取的实例都是同一个
    @Test
    public void verify_same_instance() throws ClassNotFoundException {
        Class<Person> clazz1 = Person.class;

        Class<? extends Person> clazz2 = new Person().getClass();
 
        Class<?> clazz3 = Class.forName("com.toJSE.toReflection.bean.Person");

        ClassLoader clazzLoader = Person.class.getClassLoader();
        Class<?> clazz4 = clazzLoader.loadClass("com.toJSE.toReflection.bean.Person");

        System.out.println(clazz1 == clazz2); //true
        System.out.println(clazz2 == clazz3); //true
        System.out.println(clazz3 == clazz4); //true
    }
}
