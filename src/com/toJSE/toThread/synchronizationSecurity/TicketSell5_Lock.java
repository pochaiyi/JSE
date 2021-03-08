package com.toJSE.toThread.synchronizationSecurity;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock同步锁解决3个窗口售票售票同步安全问题
 */
public class TicketSell5_Lock {
    public static void main(String[] args) {
        Lock_Ticket  lock_ticket = new Lock_Ticket();

        Thread ticket_thread51 = new Thread(lock_ticket);
        Thread ticket_thread52 = new Thread(lock_ticket);
        Thread ticket_thread53 = new Thread(lock_ticket);

        ticket_thread51.setName("线程01");
        ticket_thread52.setName("线程02");
        ticket_thread53.setName("线程03");

        ticket_thread51.start();
        ticket_thread52.start();
        ticket_thread53.start();
    }
}

class Lock_Ticket implements Runnable{
    private int ticketNum = 100;
    // 1.获取同步锁ReentrantLock对象，参数true表示公平分配资源，即先进先出队列
    private ReentrantLock lock = new ReentrantLock(true);
    @Override
    public void run() {
        while (true) {
            try {
                // 手动占用lock对象
                lock.lock();
                sell();
            }finally {
                // 手动释放Lock对象
                lock.unlock();
            }
        }
    }

    private void sell(){
        if(ticketNum>0) {
            System.out.println(Thread.currentThread().getName() + " :售出票号" + ticketNum);
            ticketNum = ticketNum - 1;
        }
    }
}
