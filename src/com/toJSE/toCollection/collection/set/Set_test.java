package com.toJSE.toCollection.collection.set;

import org.junit.Test;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Set 接口方法测试
 * 注：Set 接口没有定义新的方法
 *
 * 储存无序：无序不等于随机，元素的储存位置于元素的哈希值相关
 * 不可重复数据 ：添加的元素按 equals() 判断，相同返回 true 则不能添加
 * 注：如果不重写 equals() 方法，则使用 Object.equals()，而 Object.equals() 使用的是 "==" 的返回值
 * 注：Object.hashcode() 调用底层代码返回随机值，如果不重写则失去意义
 */
public class Set_test {
    @Test
    public void Set_test1(){
        HashSet hs = new HashSet();
        hs.add(1);
        hs.add("abc");
        hs.add(3);
        hs.add(true);
        hs.add(5);
        hs.add('a');

        Iterator itor = hs.iterator();
        while (itor.hasNext()){
            System.out.print(itor.next()+" "); //1 a abc 3 5 true
        }
    }
}
