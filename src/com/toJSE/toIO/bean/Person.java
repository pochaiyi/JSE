package com.toJSE.toIO.bean;

import java.io.Serializable;

/**
 * 可序列化对象要求：
 *   1.实现接口 Serializable
 *   2.指定类序列版本号 static final long serialVersionUID
 *   3.类的内部中所有的属性也需可序列化
 *   4.ObjectOutputStream，ObjectInputStream不能序列化 static/transient 修饰的属性
 * serialVersionUID：如果不指定序列版本号，在类运行时Java会自动给类分配序列版本号，此时对象的读写并不受影响。
 *                   但当类发生了修改，那么下次自动分配的序列版本号可能不同，此时读取Object流无法成功还原
 */
public class Person implements Serializable {
    private String name;
    private int age;
    private transient String job;
    public static final long serialVersionUID = 1009999L;

    public Person(String name, int age, String job) {
        this.name = name;
        this.age = age;
        this.job = job;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
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
