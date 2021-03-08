package com.toJSE.toIO;

import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 字节流主要用于处理二进制数据，如图片、zip、doc等
 * 使用字节流读写文本文件，可能会出现乱码，但使用字节流复制文本文件不会出现信息错误
 */
public class ByteStream_use {

    // 通过字节流进行图片的复制
    @Test
    public void byte_copy_1(){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            // File实例化
            File f11 = new File("src/com/toJSE/toIO/tmp/cool.jpg");
            File f12 = new File("src/com/toJSE/toIO/tmp/cool(1).jpg");
            // FileInputStream、FileOutputStream实例化
            fi = new FileInputStream(f11);
            fo = new FileOutputStream(f12);
            // 数据复制
            byte[] bbuf = new byte[24];
            int len;
            while ((len = fi.read(bbuf)) != -1){
                fo.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fo != null)
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }

    // 通过字节流进行文本文件的复制
    @Test
    public void byte_copy_2(){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        try {
            // File实例化
            File f11 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            File f12 = new File("src/com/toJSE/toIO/tmp/CharStream(1).txt");
            // FileInputStream、FileOutputStream实例化
            fi = new FileInputStream(f11);
            fo = new FileOutputStream(f12);
            // 数据复制
            byte[] bbuf = new byte[24];
            int len;
            while ((len = fi.read(bbuf)) != -1){
                fo.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fo != null)
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }
}
