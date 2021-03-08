package com.toJSE.toThread;

/**
 * 通过实现Runnable接口并重写Runnable.run()创建多线程
 * main线程输出1:100的奇数，myThread线程输出1:100的偶数
 */
public class Thread_create2 {
    public static void main(String[] args) {
        // 1.创建实现Runnable接口的对象
        MyRunnable myRunnable = new MyRunnable();
        // 2.使用Thread(Runnable target)方法创建线程
        Thread myThread21 = new Thread(myRunnable);
        myThread21.setName("线程01");
        myThread21.start();
        Thread.currentThread().setName("主线程");
        // 主线程阻塞，myThread21线程强制占用CPU资源
        try {
            myThread21.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 输出1-100奇数
        for(int i=1;i<=100;i++){
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+" : "+i);
            }
        }
    }
}

class MyRunnable implements Runnable{
    @Override
    public void run() {
            // 输出1-100偶数
            for(int i=1;i<=100;i++){
                if(i%2==0){
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+" : "+i);
                }
            }


    }
}
