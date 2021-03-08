package com.toJSE.toCollection.map;

import org.junit.Test;

import java.util.*;

/**
 * Map遍历
 */
public class Map_test2 {
    // 与遍历相关的三个Map方法
    @Test
    public void Map_test(){
        HashMap hm1 = new HashMap();
        hm1.put(1, "唐伯虎");
        hm1.put(2, "祝枝山");
        hm1.put(3, "柳宗元");
        hm1.put(4, "桃白白");
        hm1.put(5, "李大彪");

        // 1.获取Map的key值的Set集合
        Set set1 = hm1.keySet();
        Iterator iterator1 = set1.iterator();
        while (iterator1.hasNext()){
            System.out.print(iterator1.next()+" "); //1 2 3 4 5
        }

        // 2.获取Map的key值的Collection集合
        System.out.println();
        Collection values = hm1.values();
        Iterator iterator2 = values.iterator();
        while (iterator2.hasNext()){
            System.out.print(iterator2.next()+" "); //唐伯虎 祝枝山 柳宗元 桃白白 李大彪
        }

        // 3.获取Map的Entry的Set集合
        System.out.println();
        Set set2 = hm1.entrySet();
        Iterator iterator3 = set2.iterator();
        while (iterator3.hasNext()){
            // 返回 Entry 对象
            Object next = iterator3.next();
            Map.Entry entry = (HashMap.Entry) next;
            System.out.print(entry.getKey()+" "+entry.getValue()+" "); //1 唐伯虎 2 祝枝山 3 柳宗元 4 桃白白 5 李大彪
        }
    }
}
