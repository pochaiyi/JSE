package com.toJSE.toNet;

import org.junit.Test;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * client端发送信息到server端并显示在server端控制台显示
 */
public class TCP_test1 {

    // server端
    @Test
    public void server(){
        try (
                // 服务端socket实例化
                ServerSocket ssk = new ServerSocket(8888);
                // 设置可接受客户端的socket
                Socket sk = ssk.accept();
                // 获取接收流
                InputStream is = sk.getInputStream();

                // 可拓展的打印流,用来储存接收字节流
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ){
            // 打印接收流
            int len;
            byte[] bbuf = new byte[24];
            while ((len=is.read(bbuf))!=-1) {
                baos.write(bbuf,0,len);
            }
            System.out.println(baos.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // client端
    @Test
    public void client(){
        try (
                // socket实例化
                Socket sk = new Socket("localhost", 8888);
                // 获取输出流
                OutputStream os = sk.getOutputStream();
                ){
            os.write("你好".getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
