package com.toJSE.toException;

// 自定义异常
public class MyExpection extends Exception{
    // 版本序列号
    static final long serialVersionUID = -338751699312422998L;
    // 空参构造器
    public MyExpection(){}
    // 异常消息构造器
    public MyExpection(String msg){
        super(msg);
    }
}
