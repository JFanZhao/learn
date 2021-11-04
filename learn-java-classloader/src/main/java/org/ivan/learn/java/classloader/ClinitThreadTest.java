package org.ivan.learn.java.classloader;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 虚拟机保证类初始化的线程安全
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−11-04 21:42
 **/
public class ClinitThreadTest {
    public static void main(String[] args) {

        System.out.println(System.getProperty("sun.boot.class.path"));
//
        new Thread(() -> new SimpleObject()).start();
//
        new Thread(() -> new SimpleObject()).start();
    }


    static class SimpleObject {

        private static AtomicBoolean init = new AtomicBoolean(true);

        static {
            System.out.println(Thread.currentThread().getName() + " I will be initial");
            while (init.get()) {

            }
            System.out.println(Thread.currentThread().getName() + " I am finished initial.");
        }
    }
}


