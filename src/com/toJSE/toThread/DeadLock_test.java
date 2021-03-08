package com.toJSE.toThread;

/**
 * 死锁演示
 */
public class DeadLock_test {
    public static void main(String[] args) {
        String str1 = "Hello";
        String str2 = "你好";

        new Thread() {
            @Override
            public void run() {
                synchronized (str1) {
                    try {
                        System.out.println(Thread.currentThread().getName()+" :"+str1);
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (str2){
                        System.out.println(Thread.currentThread().getName()+" :"+str2);
                    }
                }
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (str2){
                    System.out.println(Thread.currentThread().getName()+" :"+str2);
                    synchronized (str1){
                        System.out.println(Thread.currentThread().getName()+" :"+str1);
                    }
                }
            }
        }).start();
    }
}

