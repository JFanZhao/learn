package com.ivan.learn.java.concurrency.example;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 08:50
 **/
public class ThreadJoin2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(()->{
            System.out.println(" t1 is running");
            while (true) {
                System.out.println(Thread.currentThread().isInterrupted());
            }
        });

        t1.start();

        Thread t2 = new Thread(()->{
            try {
                Thread.sleep(10000);
                t1.interrupt();
                System.out.println("interrupt");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        t2.start();

        try {
            t1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
