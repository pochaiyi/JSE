package com.toJSE.toEnum;

/**
 * 使用 Enum 关键字创建枚举类
 * 定义季节枚举类 Season
 */
public class Enum_use1 {
    public static void main(String[] args) {
        Season2 spring = Season2.SPRING;
        System.out.println(spring); //Season{season='春天'}
        // enum 类常用的3个方法
        // 1.enum.values() 返回枚举类的对象常量数组
        System.out.println(Season2.values()[0]); //Season{season='春天'}
        // 2.enum.valueof(String name) 返回枚举类中与字符串参数同名的对象常量
        System.out.println(Season2.valueOf("SUMMER")); //Season{season='夏天'}
        // 3.tostring 未覆盖之前，默认返回对象名
    }
}

enum Season2{
    SPRING("春天"),
    SUMMER("夏天"),
    AUTUMN("秋天"),
    WINTER("冬天");
    // Season对象属性
    private final String season;
    // 私有化类的构造器
    private Season2(String seasonName){
        season = seasonName;
    }
    // 其他要求：tostring
    @Override
    public String toString() {
        return "Season{" +
                "season='" + season + '\'' +
                '}';
    }
}
