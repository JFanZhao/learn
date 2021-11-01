package com.ivan.learn.java.concurrency.example.singleton;

/**
 * 同步，线程安全，效率低
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:42
 **/
public class SingletonObject3 {
    private static SingletonObject3 instance;

    private SingletonObject3() {
        //empty
    }

    public synchronized static SingletonObject3 getInstance() {

        if (null == instance)
            instance = new SingletonObject3();

        return SingletonObject3.instance;
    }
}
