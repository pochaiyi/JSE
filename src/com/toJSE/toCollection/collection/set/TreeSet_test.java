package com.toJSE.toCollection.collection.set;

/**
 * TreeSet 中添加的元素必须属于同一个类
 * TreeSet 适合用于数据排序
 *     自然排序：实现 Comparable 接口
 *     定制排序：Comparator 内部临时类
 * 自然排序中，比较两个对象是否相同，compareTo()返回0，不再是equals()
 * 定制排序中，比较两个对象是否相同，compare()返回0，不再是equals()
 */
public class TreeSet_test {
}
