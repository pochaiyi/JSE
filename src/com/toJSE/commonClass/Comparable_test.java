package com.toJSE.commonClass;

import java.util.Arrays;

/**
 * 使用Comparable接口比较大小，自然排序
 */
public class Comparable_test {
    public static void main(String[] args) {
        Student[] stuList = new Student[5];
        stuList[0] = new Student("hhh",21);
        stuList[1] = new Student("aaa",23);
        stuList[2] = new Student("ccc",13);
        stuList[3] = new Student("ddd",34);
        stuList[4] = new Student("eee",53);
        Arrays.sort(stuList);
        System.out.println(Arrays.toString(stuList));
    }
}

// 实现Comparable接口，重写compareTo方法
class Student implements Comparable{
    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // 实现Student名字比较排序
    @Override
    public int compareTo(Object o) {
        if(o instanceof Student){
            Student p = (Student)o;
            return this.name.compareTo(p.getName());
        }else throw new RuntimeException("类型不一致");
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
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
}