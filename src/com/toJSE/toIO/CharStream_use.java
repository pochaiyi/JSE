package com.toJSE.toIO;

import org.junit.Test;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 字符流主要用于处理纯文本的数据流，但不能处理如jpg、doc、zip等二进制格式的文件
 */
public class CharStream_use {

    // 通过FileWriter写出数据
    // 如果文件不存在则创建同名文件
    // FileWriter对象实例化时可以设置追加写出，默认为覆盖
    @Test
    public void char_writer(){
        FileWriter fw1 = null;
        FileWriter fw11 = null;
        try {
            // File实例化
            File f1 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            // FileWriter实例化
            fw1 = new FileWriter(f1);
            // 方式1.数据写出(覆盖)
            fw1.write("I hava a dream.");
            fw1.write("\n");
            fw1.write("you need to hava a dream.");
            // 方式2.数据写出(追加)
            fw11 = new FileWriter(f1,true);
            fw11.write("\n");
            fw11.write("我有一个梦想");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流
            if (fw1 != null)
                try {
                    fw1.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            if (fw11 != null){
                try {
                    fw11.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 通过FileReader读入数据
    // 如果文件不存在会出异常
    // java.io.InputStreamReader.read()
    @Test
    public void Char_reader_1(){
        FileReader fr2 = null;
        try {
            // File实例化
            File f2 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            // FileReader实例化
            fr2 = new FileReader(f2);
            int len;
            // 方式1.数据读入
            while ((len = fr2.read()) != -1) {
                System.out.print((char)len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr2 != null){
                try {
                    fr2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // java.io.InputStreamReader.read(char[] cbuf)
    @Test
    public void Char_reader_2(){
        FileReader fr22 = null;
        try {
            // File实例化
            File f22 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            // FileReader实例化
            fr22 = new FileReader(f22);
            // 方式2.数据读入
            char[] cbuf = new char[10];
            int len;
            while ((len = fr22.read(cbuf)) != -1){
                String str = new String(cbuf,0,len);
                System.out.print(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fr22 != null)
                try {
                    fr22.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }

    }
}
