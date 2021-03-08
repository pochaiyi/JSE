package com.toJSE.toJDBC;

import org.junit.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 通过JDBC获取数据库连接
 */
public class Connection_get {
    // 方式1
    @Test
    public void getCon_test1() throws SQLException {
        // 获取数据库实现的驱动包
        Driver driver = new com.mysql.cj.jdbc.Driver();
        // 设置连接信息：协议、ip地址、端口、数据库、用户、密码
        String url = "jdbc:mysql://localhost:3306/my_stu";
        Properties properties = new Properties();
        properties.setProperty("user","java");
        properties.setProperty("password","java");
        // 通过驱动包获取Connection对象
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    // 方式2，将固定的第三方API去除，增加可移植性
    @Test
    public void getCon_test2() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, SQLException {
        // 通过反射获取数据库实现的Driver对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();
        // 设置连接信息：协议、ip地址、端口、数据库、用户、密码
        String url = "jdbc:mysql://localhost:3306/my_stu";
        Properties properties = new Properties();
        properties.setProperty("user","java");
        properties.setProperty("password","java");
        // 通过驱动包获取Connection对象
        Connection connect = driver.connect(url, properties);
        System.out.println(connect);
    }

    // 方式3，使用DriverManager替换Driver获取Connection对象
    @Test
    public void getCon_test3() throws ClassNotFoundException, IllegalAccessException,
            InstantiationException, SQLException {
        // 设置连接信息：协议、ip地址、端口、数据库、用户、密码
        String url = "jdbc:mysql://localhost:3306/my_stu";
        Properties properties = new Properties();
        properties.setProperty("user","java");
        properties.setProperty("password","java");

        // 通过反射获取数据库实现的Driver对象
        Class clazz = Class.forName("com.mysql.cj.jdbc.Driver");
        Driver driver = (Driver) clazz.newInstance();

        // 通过DriverManager获取Connection对象
        // 注册驱动
        DriverManager.registerDriver(driver);
        Connection connection = DriverManager.getConnection(url, properties);
        System.out.println(connection);
    }

    // 方式4，省略驱动注册
    @Test
    public void getCon_test4() throws ClassNotFoundException,SQLException {
        // 设置连接信息：协议、ip地址、端口、数据库、用户、密码
        String url = "jdbc:mysql://localhost:3306/my_stu";
        Properties properties = new Properties();
        properties.setProperty("user","java");
        properties.setProperty("password","java");

        // 通过反射获取数据库实现的Driver对象：Driver在加载中执行静态代码块，故不必进行驱动注册
    //    static {
    //        try {
    //            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
    //        } catch (SQLException var1) {
    //            throw new RuntimeException("Can't register driver!");
    //        }
    //    }
        // 其实对于MySQL，Driver类加载过程也可以省略，因为其Jar包中列出了Driver路径
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, properties);
        System.out.println(connection);
    }
    
    // 方式5，使用配置文件设置连接信息，实现数据与代码的分离，避免修改信息重新编译打包
    @Test
    public void getCon_test5() throws IOException, ClassNotFoundException, SQLException {
        // 使用Properties读取配置文件
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/com/toJSE/toJDBC/conf/properties.properties")));
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");
        String driverClass = properties.getProperty("driverClass");
//        System.out.println(user+"\n"+password+"\n"+url+"\n"+driverClass);

        // 加载驱动
        Class.forName(driverClass);
        // 获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        System.out.println(connection);
    }
}
