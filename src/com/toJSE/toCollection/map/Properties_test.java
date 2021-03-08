package com.toJSE.toCollection.map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 通过Properties类处理 .properties文件
 */
public class Properties_test {
    public static void main(String[] args) {
        Properties pro = new Properties();
        try {
            // 1.获取配置文件
            File f1 = new File("src/com/toJSE/toCollection/conf/pro.properties");
            FileInputStream fis = new FileInputStream(f1);
            // 2.使用Properties加载配置文件
            pro.load(fis);
            // 3.根据key获取value
            System.out.println(pro.getProperty("1")); //唐伯虎
            System.out.println(pro.getProperty("2")); //祝枝山
            System.out.println(pro.getProperty("3")); //李大彪
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
