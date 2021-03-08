package com.toJSE.toIO;

import org.junit.Test;
import java.io.*;

/**
 * 转换流属于字符流，但是在字节节点流基础上构造
 * 用于将字节输入流转换为字符流，将字符输出流转换为字节流
 */
public class TransStream_use {

    // 使用转换流转换文本文件的编码格式由UTF-8到GBK
    @Test
    public void utf_to_gbk(){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        InputStreamReader isr = null;
        OutputStreamWriter osw = null;
        try {
            // File实例化
            File f11 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            File f12 = new File("src/com/toJSE/toIO/tmp/CharStream(3).txt");
            // 字节节点流实例化
            fi = new FileInputStream(f11);
            fo = new FileOutputStream(f12);
            // 转换流实例化
            isr = new InputStreamReader(fi,"utf-8");
            osw = new OutputStreamWriter(fo,"gbk");
            // 编码转换
            char[] cbuf = new char[24];
            int len;
            while ((len = isr.read(cbuf)) != -1){
                osw.write(cbuf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭
            if(isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(osw != null) {
                try {
                    osw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fi != null) {
                try {
                    fi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fo != null) {
                try {
                    fo.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
