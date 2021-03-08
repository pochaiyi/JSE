package com.toJSE.toCollection.collection.list;

import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

/**
 * Link接口方法测试
 */
public class List_test {
    @Test
    public void List_test1(){
        List list = new ArrayList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        System.out.println(list); //[1, 2, 3, 4, 5]
        // 1.List.add(int index, E element) 在指定位置添加元素

        list.add(0,6);
        System.out.println(list); //[6, 1, 2, 3, 4, 5]

        // 2.List.addAll(int index, Collection c) 在指定位置添加第二个参数所指容器的所有元素
        ArrayList list1 = new ArrayList();
        list1.add(10);
        System.out.println(list1); //[10]
        list1.addAll(list);
        System.out.println(list1); //[10, 6, 1, 2, 3, 4, 5]

        // 3.List.indexOf(Object o) 返回指定元素在容器中首次出现的下标
        System.out.println(list.indexOf(6)); //0

        // 4.List.lastIndexOf(Object o) 返回指定元素在容器中最后一次出现的下标
        list.add(6);
        System.out.println(list); //[6, 1, 2, 3, 4, 5, 6]
        System.out.println(list.lastIndexOf(6)); //6

        // 5.List.remove(int index) 删除指定位置的元素，后面的元素前移
        System.out.println(list); //[6, 1, 2, 3, 4, 5, 6]
        list.remove(6);
        System.out.println(list); //[6, 1, 2, 3, 4, 5]

        // 6.List.set(int index,E element) 修改指定位置的元素为第二个参数的指引
        list.set(0,8);
        System.out.println(list);

        // 7.List.subList(int fromIndex, int toIndex) 截取参数所指范围的元素，作为一个List返回，左闭右开
        List list2 = list.subList(1, 3);
        System.out.println(list); //[8, 1, 2, 3, 4, 5]
        System.out.println(list2); //[1, 2]
    }
}
