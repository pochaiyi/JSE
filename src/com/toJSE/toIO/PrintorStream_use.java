package com.toJSE.toIO;

import sun.security.util.Length;
import java.io.*;

/**
 * 打印流：PrintStream，PrintWriter 是字节流
 * 用于将基本数据类型转化为字符串进行输出，有多种重载的print、println方法
 * 标准输出流类型就是 PrintStream
 * System.out.println(),System.out.print() 实质是在调用各种重载的 print，println方法
 */
public class PrintorStream_use {
    public static void main(String[] args) {
        FileOutputStream fos = null;
        PrintStream ps = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 字节节点流实例化
            fos = new FileOutputStream(new File("src/com/toJSE/toIO/tmp/print.txt"));
            // 创建打印流并设置自动刷新
            ps = new PrintStream(fos, true);
            // 将标准输出流重定向为文件
            if(ps != null){
                System.setOut(ps);
            }
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String str;
            while (true) {
                str = br.readLine();
                if ("exit".equals(str)) {
                    break;
                }
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(ps!=null) {
                ps.close();
            }
            if(fos!=null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (isr != null) {
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
