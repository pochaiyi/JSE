package com.toJSE.toIO.file;


import org.junit.Test;
import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * java.IO.File 类的使用
 * 路径分隔符与系统有关：windows、DOS系统使用'\'，Unix和URL使用'/'
 */
public class File_test {

    // 获取File实例
    // 方法1.File(String pathname)
    @Test
    public void File_instance_get1(){

        // 绝对路径
        File f11 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\tmp\\data.txt");
        System.out.println(f11); //D:\affairs\github\JSE\src\com\toJSE\toIO\tmp\data.txt
        // 相对路径，相对于当前module
        File f12 = new File("src/com/toJSE/toIO/tmp/data.txt");
        System.out.println(f12); //src\com\toJSE\toIO\tmp\data.txt
    }

    //方法2.File(String parent, String child) {
    @Test
    public void File_instance_get2(){
        File f13 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO", "tmp");
        System.out.println(f13); //D:\affairs\github\JSE\src\com\toJSE\toIO\tmp
    }

    //方法3.
    @Test
    public void File_instance_get3(){
        File f13 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO", "tmp");
        File f14 = new File(f13, "tmp\\data.txt");
        System.out.println(f14); //D:\affairs\github\JSE\src\com\toJSE\toIO\tmp\tmp\data.txt
    }

    // File类常用方法
    @Test
    public void File_use1(){
        File f21 = new File("src/com/toJSE/toIO/tmp/data.txt");
        // 1.绝对路径
        System.out.println(f21.getAbsolutePath()); //D:\affairs\github\JSE\src\com\toJSE\toIO\tmp\data.txt

        // 2.相对路径
        System.out.println(f21.getPath()); //src\com\toJSE\toIO\tmp\data.txt

        // 3.文件(夹)名
        System.out.println(f21.getName()); //data.txt

        // 4.上级目录路径
        System.out.println(f21.getParent()); //src\com\toJSE\toIO\tmp

        // 5.文件(夹)长度，即储存空间
        System.out.println(f21.length()); //11

        // 6.文件(夹)上次修改时间
        System.out.println(f21.lastModified()); //1614322976622
        System.out.println(new Date(f21.lastModified())); //Fri Feb 26 15:02:56 CST 2021
    }

    @Test
    public void File_use2(){
        File f21 = new File("src/com/toJSE/toIO/tmp");
        // 7.获取指定目录下所有文件(夹)的名称数组，即返回String数组
        String[] list = f21.list();
        for(String str:list){
            System.out.print(str+" ");
        }

        // 8.获取指定目录下所有文件(夹)的File数组
        File[] files = f21.listFiles();
        for(File file:files){
            System.out.print(file+" ");
        }

        // 9.更改文件名称，实质上是文件的移动，参照Linxu的改名操作
        File f22 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\tmp\\data.txt");
        File f23 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\data_.txt");
        boolean renameTo = f22.renameTo(f23);
//        boolean renameTo = f23.renameTo(f22);
        System.out.println(renameTo);
    }

    @Test
    public void File_use3(){
        File f31 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\tmp\\data.txt");
        // 10.判断文件
        System.out.println(f31.isFile());

        // 11.判断文件夹
        System.out.println(f31.isDirectory());

        // 12.判断存在
        System.out.println(f31.exists());

        // 14.判断可读
        System.out.println(f31.canRead());

        // 15.判断可写
        System.out.println(f31.canWrite());

        // 16.判断隐藏
        System.out.println(f31.isHidden());
    }

    @Test
    public void File_use4(){
        // 1.创建/删除文件
        File f41 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\file\\test1.txt");
        if(!f41.exists()){
            try {
                if(f41.createNewFile()){
                    System.out.println("创建成功");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            f41.delete();
            System.out.println("删除成功");
        }

        // 2.创建文件夹
        File f42 = new File("D:\\affairs\\github\\JSE\\src\\com\\toJSE\\toIO\\file\\tmp1");
        f42.mkdir();
        // 与mkdir()相比,会帮助创建不存在的父目录
        f42.mkdirs();
        f42.delete();

    }
}
