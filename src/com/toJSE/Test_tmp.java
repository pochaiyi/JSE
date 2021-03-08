package com.toJSE;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test_tmp {

    @Test
    public void test1(){
        System.out.println(System.getProperty("java.version")); //1.8.0_111
        System.out.println(System.getProperty("java.home")); //D:\installs\JDK\jdk1.8.0_111\jre
        System.out.println(System.getProperty("os.name")); //Windows 10
        System.out.println(System.getProperty("os.version")); //10.0
        System.out.println(System.getProperty("user.name")); //lzd
        System.out.println(System.getProperty("user.home")); //C:\Users\lzd
        System.out.println(System.getProperty("user.dir")); //D:\affairs\github\JSE
    }

    @Test
    public void test2(){
        List<?> l1;
        List<String> l2 = new ArrayList<>();
        l2.add("aaa");
        l2.add("bbb");
        l2.add("ccc");
        l2.add("ddd");
        l2.add("eee");
//        System.out.println(l2);
        l1 = l2;
//        l1.add("fff");
        l1.add(null);
        System.out.println(l1.get(1));
        System.out.println(l1.get(5));
    }
}
