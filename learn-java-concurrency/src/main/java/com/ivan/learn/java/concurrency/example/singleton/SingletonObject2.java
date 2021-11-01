package com.ivan.learn.java.concurrency.example.singleton;

/**
 * 线程不安全
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:37
 **/
public class SingletonObject2 {

    private static SingletonObject2 instance;

    private SingletonObject2() {

    }

    public static SingletonObject2 getInstance() {
        if (null == instance) {
            instance = new SingletonObject2();
        }
        return SingletonObject2.instance;
    }
}
