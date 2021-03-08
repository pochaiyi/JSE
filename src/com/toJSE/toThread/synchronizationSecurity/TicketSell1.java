package com.toJSE.toThread.synchronizationSecurity;

/**
 * 同步代码块解决3个窗口售票售票同步安全问题，Runnable接口
 */
public class TicketSell1 {
    public static void main(String[] args) {
        // 所有Thread对象都要使用同一把同步锁
        Runnable_Ticket1 runnable_ticket1 = new Runnable_Ticket1();

        Thread ticket_thread11 = new Thread(runnable_ticket1);
        Thread ticket_thread12 = new Thread(runnable_ticket1);
        Thread ticket_thread13 = new Thread(runnable_ticket1);

        ticket_thread11.setName("线程01");
        ticket_thread12.setName("线程02");
        ticket_thread13.setName("线程03");

//        ticket_thread03.setPriority(Thread.MAX_PRIORITY);

        ticket_thread11.start();
        ticket_thread12.start();
        ticket_thread13.start();
    }
}

class Runnable_Ticket1 implements Runnable{
    private int ticketNum = 1000;
    @Override
    public void run() {
        while (true) {
            // 同步代码块，通常使用this对象作为同步监视器，任何一个类的对象都可以作为同步监视器
            synchronized(this) {
                if(ticketNum>0) {
                    System.out.println(Thread.currentThread().getName() + " :售出票号" + ticketNum);
                    ticketNum = ticketNum - 1;
                }else break;
            }
        }
    }
}
