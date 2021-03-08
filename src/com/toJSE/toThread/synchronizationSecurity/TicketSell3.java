package com.toJSE.toThread.synchronizationSecurity;

/**
 * 同步方法解决3个窗口售票售票同步安全问题，Runnable接口
 */
public class TicketSell3 {
    public static void main(String[] args) {
        // 所有Thread对象都要使用同一把同步锁
        Runnable_Ticket2 runnable_ticket2 = new Runnable_Ticket2();

        Thread ticket_thread31 = new Thread(runnable_ticket2);
        Thread ticket_thread32 = new Thread(runnable_ticket2);
        Thread ticket_thread33 = new Thread(runnable_ticket2);

        ticket_thread31.setName("线程01");
        ticket_thread32.setName("线程02");
        ticket_thread33.setName("线程03");

        ticket_thread31.start();
        ticket_thread32.start();
        ticket_thread33.start();
    }
}

class Runnable_Ticket2 implements Runnable{
    private int ticketNum = 1000;
    @Override
    public void run() {
        while (true) {
            sell();
        }
    }

    // 同步方法，这里使用this作为同步监视器
    private synchronized void sell(){
//        synchronized(this) {
        if(ticketNum>0) {
            System.out.println(Thread.currentThread().getName() + " :售出票号" + ticketNum);
            ticketNum = ticketNum - 1;
        }
    }
//    }
}
