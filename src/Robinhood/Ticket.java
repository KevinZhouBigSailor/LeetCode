package Robinhood;

import java.util.concurrent.atomic.AtomicInteger;

public class Ticket {
    static AtomicInteger test = new AtomicInteger(10);
    //public static int test = 10;

    public static void main(String[] args) {
        Thread thread1 = new Thread(new sellerMachine("seller1"));
        Thread thread2 = new Thread(new sellerMachine("seller2"));
        thread1.start();
        thread2.start();

    }


}

class sellerMachine implements Runnable {
    String name;

    sellerMachine(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        /*while (Ticket.test>0) {
            Ticket.test--;
            System.out.println(name + " " + Ticket.test);
        }*/
        while (Ticket.test.get() > 0) {
            Ticket.test.decrementAndGet();
            System.out.println(name + " " + Ticket.test.get());
        }
    }
}