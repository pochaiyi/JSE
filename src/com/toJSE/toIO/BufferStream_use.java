package com.toJSE.toIO;

import org.junit.Test;
import java.io.*;

/**
 * 缓冲流是在节点流的基础上构造，用于提高数据读写的速度
 */
public class BufferStream_use {

    // 使用字节缓冲流实现文件复制
    @Test
    public void byte_buffer_copy(){
        FileInputStream fi = null;
        FileOutputStream fo = null;
        BufferedInputStream bi = null;
        BufferedOutputStream bo = null;
        try {
            // File实例化
            File f11 = new File("src/com/toJSE/toIO/tmp/CharStream.rar");
            File f12 = new File("src/com/toJSE/toIO/tmp/CharStream(1).rar");
            // 字节节点流实例化
            fi = new FileInputStream(f11);
            fo = new FileOutputStream(f12);
            // 字节缓冲流实例化
            bi = new BufferedInputStream(fi);
            bo = new BufferedOutputStream(fo);
            // 数据复制
            byte[] bbuf = new byte[24];
            int len;
            while ((len = bi.read(bbuf)) != -1){
                bo.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭，先关闭外层后关闭内层
            if(bi != null) {
                try {
                    bi.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bo != null) {
                try {
                    bo.close();
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

    // 使用字符缓冲流实现文本文件复制
    @Test
    public void char_buffer_copy(){
        FileReader fr = null;
        FileWriter fw = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        try {
            // File实例化
            File f21 = new File("src/com/toJSE/toIO/tmp/CharStream.txt");
            File f22 = new File("src/com/toJSE/toIO/tmp/CharStream(2).txt");
            // 字符节点流实例化
            fr = new FileReader(f21);
            fw = new FileWriter(f22);
            // 字符缓冲流实例化
            br = new BufferedReader(fr);
            bw = new BufferedWriter(fw);
            // 数据复制
            char[] cbuf = new char[24];
            int len;
            while ((len = br.read(cbuf)) != -1){
                bw.write(cbuf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
