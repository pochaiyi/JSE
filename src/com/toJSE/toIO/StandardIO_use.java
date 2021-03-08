package com.toJSE.toIO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 标准I/O流属于字节流
 * System.in    标准输入流   键盘      InputStream
 * System.out   标准输出流   控制台    PrintStream
 */
public class StandardIO_use {

    // 使用标准I/O将键盘输入内容打印到控制台
    public static void main(String[] args) {
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            // 流实例化
            isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
            String str;
            while (true) {
                System.out.print("print:  ");
                str = br.readLine();
                if ("exit".equals(str)) {
                    System.out.println("Bye");
                    break;
                }
                System.out.println(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 资源关闭
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
