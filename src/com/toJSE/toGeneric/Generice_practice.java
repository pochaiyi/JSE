package com.toJSE.toGeneric;


import java.util.HashMap;

public class Generice_practice {

}

// 使用泛型
class Dao<T>{
    private HashMap<String,T> data = new HashMap<String,T>();
}