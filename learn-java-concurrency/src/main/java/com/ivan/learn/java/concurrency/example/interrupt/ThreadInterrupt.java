package com.ivan.learn.java.concurrency.example.interrupt;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 09:41
 **/
public class ThreadInterrupt {
    public static void main(String[] args) {

        Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {

                }
            }
        };

        t.start();
        Thread main = Thread.currentThread();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                main.interrupt();
                System.out.println("interrupt");
            }
        };

        t2.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
