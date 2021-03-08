package com.toJSE.toThread;

/**
 * 通过继承Thread并重写run()创建多线程
 */
public class Thread_create1 {
    public static void main(String[] args) {
        MyThread1 myThread_11 = new MyThread1();
        myThread_11.setName("线程01");
        // 通过匿名类创建一个临时线程
        Thread myThread_12 = new Thread() {
            @Override
            public void run() {
                for(int i=1;i<=50;i++) {
                    System.out.println(Thread.currentThread().getName() + " : " + "Hello");
                }
            }
        };
        myThread_12.setName("线程02");
        myThread_11.start();
        myThread_12.start();
        Thread.currentThread().setName("主线程");
        // 输出1-100奇数
        for(int i=1;i<=100;i++){
            if(i%2!=0){
                System.out.println(Thread.currentThread().getName()+" : "+i);
            }
        }
    }
}

class MyThread1 extends Thread{
    // 重写run()
    @Override
    public void run() {
        // 输出1-100偶数
        for(int i=1;i<=100;i++){
            if(i%2==0){
                System.out.println(Thread.currentThread().getName()+" : "+i);
//                System.out.println(this.getName());
            }
        }
    }
}
