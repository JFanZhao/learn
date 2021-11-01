package com.ivan.learn.java.concurrency.example.singleton;

/**
 * volatile 修饰，保证有序
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:47
 **/
public class SingletonObject5 {
    private static volatile SingletonObject5 instance;

    private SingletonObject5() {
        //
    }

    //double check add volatile
    public static SingletonObject5 getInstance() {

        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance)
                    instance = new SingletonObject5();
            }
        }
        return SingletonObject5.instance;
    }
}
