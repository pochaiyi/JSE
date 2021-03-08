package com.toJSE.toCollection.collection;

import org.junit.Test;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;

/**
 *  Collection接口方法测试
 */
public class Collection_test {
    @Test
    public void Collection_test1(){
        Collection coll = new LinkedList();
        // 1.add(Object e)：添加元素
        coll.add("abc");
        coll.add(123);
        coll.add(new Date());

        // 2.size()：获取元素个数
        System.out.println(coll.size()); //3

        // 3.addAll(Collection c)：添加参数所指容器的全部元素
        Collection coll1 = new LinkedList();
        coll1.add("efg");
        coll.addAll(coll1);
        System.out.println(coll.size()); //4

        // 4.isEmpty()：判断为空
        System.out.println(coll.isEmpty()); //false

        // 5.clear()：清空容器元素
        coll.clear();
        System.out.println(coll.isEmpty()); //true

        // 6.contains(Object e)：判断包含某个元素(内容)，"=="判断
        coll.add("hi");
        coll.contains(123); //false
        coll.contains("hi"); //true
        System.out.println(coll.contains(new String("hi"))); //true

        // 7.containsAll(Collection c)：判断容器是否包含参数所指容器的全部元素
        coll.addAll(coll1);
        System.out.println(coll.containsAll(coll1)); //true

        // 8.remove(Object e)：删除某个元素，删除成功返回ture，否则返回false，"=="判断
        coll.clear();
        coll.add(123);
        coll.add("abc");
        System.out.println(coll); //[123, abc]
        System.out.println(coll.remove(123)); //true
        System.out.println(coll.remove("efg")); //false
        System.out.println(coll); //[abc]

        // 9.removeAll(Collection c)：删除容器中参数所指容器含有的全部元素，即删去交集，"=="判断
        coll.addAll(coll1); //[abc]
        System.out.println(coll1); //[efg]
        System.out.println(coll); //[abc, efg]
        coll.removeAll(coll1);
        System.out.println(coll); //[abc]

        // 10.retainAll(Collection c)：保留交集，"=="判断
        coll.addAll(coll1);
        System.out.println(coll); //[abc, efg]
        System.out.println(coll1); //[efg]
        coll.retainAll(coll1);
        System.out.println(coll); //[efg]

        // 11.equals(Collection c)：判断两个容器元素是否相同，是否判断顺序分情况
        System.out.println(coll.equals(coll1)); //true

        // 12.hashCode()：返回容器的哈希值
        System.out.println(coll.hashCode()); //100357

        // 13.toArray()：集合 -> object数组，返回数组
        System.out.println(coll); //[efg]
        for(Object e:coll.toArray()){
            System.out.print(e); //efg
        }

        // 14.toArray(T[] a)

        // 15.iterator()：返回iterator实例，用于遍历集合元素
    }

    // 使用迭代器 iterator 遍历容器类
    @Test
    public void Collection_test2(){
        Collection coll2 = new LinkedList();
        coll2.add("a");
        coll2.add("b");
        coll2.add("c");
        coll2.add("d");
        coll2.add("e");
        coll2.add("f");
        coll2.add("g");

        // 1.获取迭代器
        Iterator itor = coll2.iterator();
        System.out.println(coll2); //[a, b, c, d, e, f, g]
        // hashNext() 判断指针下一位是否有元素，有则返回ture，否则返回false
        while(itor.hasNext()){
            Object e = itor.next();
            if("g".equals(e)){
                // remove() 从容器中删除当前指针指向的元素
                itor.remove();
            }else {
                // next.next() 指针向下移，并返回所指向位置的元素
                System.out.print(e+" ");
            }
        }
        System.out.println(coll2); //[a, b, c, d, e, f]
    }
}
