package com.toJSE.toIO;

import com.toJSE.toIO.bean.Person;
import org.junit.Test;
import java.io.*;

/**
 * 对象流：ObjectOutputStream，ObjectInputStream
 * 可将Java中的对象转换为二进制流进行储存、传输
 */
public class ObjectStream_use {

    // 通过ObjectOutputStream写出内置、自定义对象
    @Test
    public void boject_out(){
        ObjectOutputStream oos = null;
        Person p1 = new Person("唐伯虎", 28 ,"画家");
        try {
            oos = new ObjectOutputStream(new FileOutputStream("src/com/toJSE/toIO/tmp/person"));
            oos.writeObject("我有一个梦想!");
            oos.writeObject(p1);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(oos!=null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 通过ObjectInputStream读入内置、自定义对象
    @Test
    public void boject_in(){
        ObjectInputStream ois = null;
        Person p2;
        try {
            ois = new ObjectInputStream(new FileInputStream("src/com/toJSE/toIO/tmp/person"));
            String a = (String) ois.readObject();
            p2 = (Person) ois.readObject();
            System.out.println(a);
            System.out.println(p2);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if(ois!=null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
