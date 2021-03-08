package com.toJSE.toReflection;

import com.toJSE.toReflection.bean.Person;
import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 通过运行时类调用特定的(静态)属性、(静态)方法、构造器，获取运行时类的接口、所在包、注解
 */
public class Class_instance_use1 {

    // 1.获取运行时类的对象的特定属性
    @Test
    public void re_use1() throws Exception {
        // 获取类对象
        Class<?> clazz1 = Class.forName("com.toJSE.toReflection.bean.Person");
        // 通过类对象调用空参构造器构造一个对象
        Person person1 = (Person)clazz1.newInstance();
        // 通过类对象调用指定属性
        Field name = clazz1.getDeclaredField("name");
        Field age = clazz1.getDeclaredField("age");
        Field job = clazz1.getDeclaredField("job");
        // 设置属性可访问
        name.setAccessible(true);
        age.setAccessible(true);
        job.setAccessible(true);
        // 属性赋值
        name.set(person1,"唐伯虎");
        age.set(person1,33);
        job.set(person1,"画家、诗人");
        // 获取属性值
        System.out.println(name.get(person1)); //唐伯虎
        System.out.println(age.get(person1)); //33
        System.out.println(job.get(person1)); //画家、诗人
        // 验证
        System.out.println(person1); //Person{name='唐伯虎', age=33, job='画家、诗人'}
    }

    // 2.获取运行时类的对象的特定方法
    @Test
    public void re_use2() throws Exception {
        // 获取类对象
        Class<?> clazz2 = Class.forName("com.toJSE.toReflection.bean.Person");
        // 通过类对象调用空参构造器构造一个对象
        Person person2 = (Person)clazz2.newInstance();
        // 获取方法
        Method setName = clazz2.getDeclaredMethod("setName", String.class);
        Method setAge = clazz2.getDeclaredMethod("setAge", int.class);
        Method setJob = clazz2.getDeclaredMethod("setJob", String.class);
        // 设置方法可访问
        setName.setAccessible(true);
        setAge.setAccessible(true);
        setJob.setAccessible(true);
        // 调用方法
        setName.invoke(person2,"唐伯虎");
        setAge.invoke(person2,40);
        setJob.invoke(person2,"流氓");
        // 验证
        System.out.println(person2); //Person{name='唐伯虎', age=40, job='流氓'}
    }

    // 3.调用运行时类特定构造器,一般使用空参构造器 Class.newInstance()
    @Test
    public void re_use3() throws Exception {
        Class<?> clazz3 = Class.forName("com.toJSE.toReflection.bean.Person");
        Constructor<?> cons = clazz3.getDeclaredConstructor(String.class, int.class, String.class);
        // 设置构造器可访问
        cons.setAccessible(true);
        // 创建运行时类对象
        Object person3 = cons.newInstance("唐伯虎", 50, "混混");
        // 验证
        System.out.println(person3); //Person{name='唐伯虎', age=50, job='混混'}
    }

    // 4.调用运行时类的静态属性、静态方法
    // invoke() 方法第一个参数 Object 必须有，静态调用时第一个参数可以是 运行时类的类对象、null
    @Test
    public void re_use4() throws Exception {
        Class<?> clazz4 = Class.forName("com.toJSE.toReflection.bean.Person");
        // 获取静态属性
        Field who = clazz4.getDeclaredField("who");
        // 设置静态属性可访问
        who.setAccessible(true);
        // 调用静态属性
        System.out.println(who.get(clazz4)); //好人
        System.out.println(who.get(null)); //好人
        who.set(null,"坏人");
        System.out.println(who.get(null)); //坏人
        // 获取静态方法
        Method introduce = clazz4.getDeclaredMethod("introduce");
        // 设置静态方法可访问
        introduce.setAccessible(true);
        // 调用静态方法
        introduce.invoke(clazz4); //我是一个好人
        introduce.invoke(null); //我是一个好人
    }

    // 5.获取运行时类的接口
    @Test
    public void re_user5() throws Exception {
        Class<?> clazz5 = Class.forName("com.toJSE.toReflection.bean.Person");
        Class<?>[] inter = clazz5.getInterfaces();
        for(Class T : inter){
            System.out.println(T); //interface java.io.Serializable
        }
    }

    // 6.获取运行时类的所在包
    @Test
    public void re_user6() throws Exception {
        Class<?> clazz6 = Class.forName("com.toJSE.toReflection.bean.Person");
        Package Package = clazz6.getPackage();
        System.out.println(Package); //package com.toJSE.toReflection.beanOFreflection
    }

    // 7.获取运行时类的注解
    @Test
    public void re_user7() throws Exception {
        Class<?> clazz7 = Class.forName("com.toJSE.toReflection.bean.Person");
        Annotation[] annotations = clazz7.getAnnotations();
        for(Annotation A : annotations){
            System.out.println(A); //@com.toJSE.toReflection.beanOFreflection.An1(value=An1)
        }
    }
}
