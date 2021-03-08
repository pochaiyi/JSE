package com.toJSE.toEnum;

/**
 * 自定义枚举类，jdk1.5之前枚举类的创建方法
 * 定义季节枚举类 Season
 */
public class DefineEnum {
    public static void main(String[] args) {
        Season1 spring = Season1.SPRING;
        System.out.println(spring); //Season{season='春天'}
    }
}

class Season1{
    // Season对象属性
    private final String season;
    // 私有化类的构造器
    private Season1(String seasonName){
        season = seasonName;
    }
    // 定义枚举类的多个对象
    public static final Season1 SPRING = new Season1("春天");
    public static final Season1 SUMMER = new Season1("夏天");
    public static final Season1 AUTUMN = new Season1("秋天");
    public static final Season1 WINTER = new Season1("冬天");
    // 其他要求：tostring
    @Override
    public String toString() {
        return "Season{" +
                "season='" + season + '\'' +
                '}';
    }
}