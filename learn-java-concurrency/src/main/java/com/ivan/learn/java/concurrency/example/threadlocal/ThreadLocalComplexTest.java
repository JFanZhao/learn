package com.ivan.learn.java.concurrency.example.threadlocal;

import java.util.Random;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 22:01
 **/
public class ThreadLocalComplexTest {
    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    //seed
    private final static Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            threadLocal.set("Thread-T1");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread t2 = new Thread(() -> {
            threadLocal.set("Thread-T2");
            try {
                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println("============");
        System.out.println(Thread.currentThread().getName() + " " + threadLocal.get());
    }
}
