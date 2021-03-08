package com.toJSE.commonClass;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 使用Comparator接口比较大小，定制排序
 */
public class Comparator_use {
    public static void main(String[] args) {
        Good[] goodList = new Good[6];
        goodList[0] = new Good("phone",3459);
        goodList[1] = new Good("fish",28);
        goodList[2] = new Good("pen",2);
        goodList[3] = new Good("book",32);
        goodList[4] = new Good("macBook",8459);
        goodList[5] = new Good("aBook",8459);
        Arrays.sort(goodList, new Comparator<Good>() {
            // 使用匿名类创建一个比较器
            @Override
            public int compare(Good o1, Good o2) {
                if (o1 instanceof Good && o2 instanceof Good) {
                    if (o1.getPrice() > o2.getPrice())
                        return 1;
                    else if (o1.getPrice() < o2.getPrice())
                        return -1;
                    else return (o1.getName().compareTo(o2.getName()));
                }else throw new RuntimeException("类型不一致");
            }
        });
        System.out.println(Arrays.toString(goodList));
    }
}

class Good{
    private String name;
    private double price;

    public Good(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Good{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}


