package com.toJSE.toCollection;

import org.junit.Test;

import java.util.*;

/**
 * Collections 是操作Collection和Map的工具类
 */
public class Collections_test {
    @Test
    public void test1(){
        List list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);

        // 1.Collections.reverse(List list)：反转list集合元素
        System.out.println(list1); //[1, 2, 3, 4, 5]
        Collections.reverse(list1);
        System.out.println(list1); //[5, 4, 3, 2, 1]

        // 2.Collections.shuffle(List list)：随机排序list集合元素，即洗牌
        Collections.shuffle(list1);
        System.out.println(list1); //[1, 4, 3, 5, 2]

        // 3.Collections.sort(List list)：根据自然顺序排列list集合元素
        Collections.sort(list1);
        System.out.println(list1); //[1, 2, 3, 4, 5]

        // 4.Collections.sort(list, Comparator)：根据定制排序排列list集合元素

        // 5.Collections.frequency(Collection c, Object o)：返回集合中指定元素的出现次数
        list1.add(1);
        list1.add(1);
        int fre = Collections.frequency(list1, 1);
        System.out.println(list1); //[1, 2, 3, 4, 5, 1, 1]
        System.out.println(fre); //3

        // 6.copy(List dest, List src)：复制list集合所有元素到另一个list集合中
        List<Object> list2 = Arrays.asList(new Object[list1.size()]);
        Collections.copy(list2,list1);
        System.out.println(list2); //[1, 2, 3, 4, 5, 1, 1]
        System.out.println(list1); //[1, 2, 3, 4, 5, 1, 1]

        // 7.Collections.synchronized***()：将list、map类转化为线程安全并返回

    }
}
