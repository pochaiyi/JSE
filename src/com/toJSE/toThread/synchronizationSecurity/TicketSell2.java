package com.toJSE.toThread.synchronizationSecurity;

/**
 * 同步代码块解决3个窗口售票售票同步安全问题，继承Thread
 */
public class TicketSell2 {
    public static void main(String[] args) {
        Thread ticket_thread21 = new Thread_Ticket1();
        Thread ticket_thread22 = new Thread_Ticket1();
        Thread ticket_thread23 = new Thread_Ticket1();

        ticket_thread21.setName("线程01");
        ticket_thread22.setName("线程02");
        ticket_thread23.setName("线程03");

        ticket_thread21.start();
        ticket_thread22.start();
        ticket_thread23.start();
    }
}

class Thread_Ticket1 extends Thread{
    private static int ticketNum = 1000;
    private static Object o = new Object();
    @Override
    public void run() {
        while (true) {
            // 静态属性Object保证操作ticketNum的线程都使用同一个同步监视器
//            synchronized(o) {
            synchronized (Thread_Ticket1.class) {
                if(ticketNum>0) {
                    System.out.println(Thread.currentThread().getName() + " :售出票号" + ticketNum);
                    ticketNum = ticketNum - 1;
                }else break;
            }
        }
    }
}