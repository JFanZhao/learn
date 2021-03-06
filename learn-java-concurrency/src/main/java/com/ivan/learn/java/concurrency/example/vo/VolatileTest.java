package com.ivan.learn.java.concurrency.example.vo;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:56
 **/
public class VolatileTest {
    private static volatile int INIT_VALUE = 0;

    private final static int MAX_LIMIT = 500;

    public static void main(String[] args) {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT) {
                if (localValue != INIT_VALUE) {
                    System.out.printf("The value updated to [%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "READER").start();

        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (INIT_VALUE < MAX_LIMIT) {
                System.out.printf("Update the value to [%d]\n", ++localValue);
                INIT_VALUE = localValue;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "UPDATER").start();
    }
}
