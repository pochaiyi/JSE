package com.toJSE.toReflection.bean;

import java.io.Serializable;

/**
 * Person工具类
 */
@An1
public class Person implements Serializable{

    public String name;
    int age;
    private String job;
    private static String who = "好人";

    public Person() {
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    private void setJob(String job) {
        this.job = job;
    }

    void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    int getAge() {
        return age;
    }

    private String getJob() {
        return job;
    }

    private static void introduce(){
        System.out.println("我是一个好人");
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                '}';
    }
}