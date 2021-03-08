package com.toJSE.toThread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 通过实现Runnable接口并重写Runnable.run()创建多线程
 * main线程输出1:100的奇数，myThread线程输出1:100的偶数
 */
public class Thread_create3 {
    public static void main(String[] args) {

        MyCallable myCallable = new MyCallable();
        FutureTask futureTask = new FutureTask(myCallable);

        // 启动线程，FutureTask实现了runnable接口
        new Thread(futureTask).start();
        try {
            // 调用FutureTask.get()获取形参Callable实现类的返回值
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}

// 实现Callable接口，重写call()方法
class MyCallable implements Callable{
    @Override
    public Object call() throws Exception {
        int sum = 0;
        for (int i=1;i<=100;i++){
            if(i%2==0){
                System.out.println(i);
                sum += i;
            }
        }
        return sum;
    }
}
