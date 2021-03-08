package com.toJSE.toNet;

import org.junit.Test;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 客户端发送一个文件到服务端
 */
public class TCP_test2 {

    // server端
    @Test
    public void server(){
        try (
                // server端实例化
                ServerSocket ssk = new ServerSocket(8888);
                // 设置server可接收socket
                Socket sk = ssk.accept();
                // 获取socke输入流
                InputStream is = sk.getInputStream();
                // 输出流实例化，用于保存获得的文件
                FileOutputStream fos = new FileOutputStream(new File("src/com/toJSE/toNet/tmp/cool(1).jpg"));
                ){
            byte[] bbuf = new byte[1024];
            int len;
            while ((len=is.read())!=-1) {
                fos.write(bbuf,0,len);
            }
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
                // 获取socket输出流
                OutputStream os = sk.getOutputStream();
                // 输入流实例化，用来读取需要传输的文件
                FileInputStream fis = new FileInputStream(new File("src/com/toJSE/toNet/tmp/cool.jpg"));
                ){
            // 文件传输
            byte[] bbuf = new byte[1024];
            int len;
            while ((len=fis.read(bbuf))!=-1){
                os.write(bbuf,0,len);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
