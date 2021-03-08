package com.toJSE.toThread.synchronizationSecurity;

/**
 * 同步代码块解决3个窗口售票售票同步安全问题，继承Thread
 */
public class TicketSell4 {
    public static void main(String[] args) {
        Thread ticket_thread41 = new Thread_Ticket2();
        Thread ticket_thread42 = new Thread_Ticket2();
        Thread ticket_thread43 = new Thread_Ticket2();

        ticket_thread41.setName("线程01");
        ticket_thread42.setName("线程02");
        ticket_thread43.setName("线程03");

        ticket_thread41.start();
        ticket_thread42.start();
        ticket_thread43.start();
    }
}

class Thread_Ticket2 extends Thread{
    private static int ticketNum = 100;
    @Override
    public void run() {
        while (true) {
            sell();
        }
    }

    // 同步方法，这里使用Thread_Ticket2.class作为同步监视器
    private synchronized static void sell(){
        if(ticketNum>0) {
            System.out.println(Thread.currentThread().getName() + " :售出票号" + ticketNum);
            ticketNum = ticketNum - 1;
        }
    }
}