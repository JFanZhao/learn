package com.ivan.learn.java.concurrency.example.threadlocal;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 21:59
 **/
public class ThreadLocalSimpleTest {

    private static ThreadLocal<String> threadLocal = new ThreadLocal<String>(){
        @Override
        protected String initialValue() {
            return "ivan";
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(1000);
        String s = threadLocal.get();
        System.out.println(s);
    }
}
