package com.toJSE.toIO;

import org.junit.Test;
import java.io.*;

/**
 * 数据流是处理流，在字节节点流的基础上构造
 * 用于储存基本数据类型和字符串
 * DataInputStream,DataOutputStream
 */
public class DataStream_use {

    // 通过DataOutputStream储存数据
    @Test
    public void Data_write(){
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream("src/com/toJSE/toIO/tmp/data.txt"));
            int a = 10;
            dos.writeInt(a);
            String b = "nihao";
            dos.writeUTF(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dos!=null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 通过DataInputStream读取数据
    @Test
    public void Data_read(){
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream("src/com/toJSE/toIO/tmp/data.txt"));
            // 读取数据的顺序与写入时一致
            int a = dis.readInt();
            String b = dis.readUTF();
            System.out.println(a);
            System.out.println(b);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(dis!=null) {
                try {
                    dis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
