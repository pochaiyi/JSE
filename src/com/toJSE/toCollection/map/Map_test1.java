package com.toJSE.toCollection.map;

import org.junit.Test;
import java.util.HashMap;

/**
 * Map接口方法测试
 */
public class Map_test1 {
    @Test
    public void Map_test1() {
        // 1.HashMap.put(K key, V value)：添加元素，如果已有key值存在则覆盖旧value
        HashMap hm1 = new HashMap();
        hm1.put(1, "唐伯虎");
        hm1.put(2, "祝枝山");
        hm1.put(3, "柳宗元");
        hm1.put(4, "桃白白");
        hm1.put(5, "李大彪");
        System.out.println(hm1); //{1=唐伯虎, 2=祝枝山, 3=柳宗元, 4=桃白白, 5=李大彪}
        // put()修改
        hm1.put(5, "龟仙人");
        System.out.println(hm1); //{1=唐伯虎, 2=祝枝山, 3=柳宗元, 4=桃白白, 5=龟仙人}

        // 2.HashMap.remove(Object key)：删除指定key的键值对
        hm1.remove(5);
        System.out.println(hm1); //{1=唐伯虎, 2=祝枝山, 3=柳宗元, 4=桃白白}

        // 3.HashMap.clear()：清空容器键值对
        hm1.clear();
        System.out.println(hm1); //{}
    }

    @Test
    public void Map_test2(){

        HashMap hm21 = new HashMap();
        hm21.put(1, "唐伯虎");
        hm21.put(2, "祝枝山");
        hm21.put(3, "柳宗元");
        hm21.put(4, "桃白白");
        hm21.put(5, "李大彪");

        // 4.HashMap.get(Object key)：获取指定key的value
        System.out.println(hm21.get(5)); //李大彪

        // 5.HashMap.containsKey(Object key)：判断是否含有指定的key
        System.out.println(hm21.containsKey(5)); //true
        System.out.println(hm21.containsKey(6)); //false

        // 6.HashMap.containsValue(Object value) //判断是否含有指定的value
        System.out.println(hm21.containsValue("李大彪")); //true
        System.out.println(hm21.containsValue("李小彪")); //false

        // 7.HashMap.equals(Object o)：判断两个Map是否相等,即所有键值对相同，与元素添加顺序无关
        HashMap hm22 = new HashMap();
        System.out.println(hm21.equals(hm22)); //false
        hm22.put(1, "唐伯虎");
        hm22.put(2, "祝枝山");
        hm22.put(3, "柳宗元");
        hm22.put(5, "李大彪");
        hm22.put(4, "桃白白");
        System.out.println(hm21.equals(hm22)); //true

        // 8.HashMap.isEmpty()：判断Map是否为空
        System.out.println(hm21.isEmpty()); //false
        hm21.clear();
        System.out.println(hm21.isEmpty()); //true
    }

}
