package com.ivan.learn.java.concurrency.example.singleton;

/**
 * double check.可能因为重排序优化造成空指针异常
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:44
 **/
public class SingletonObject4 {

    private static SingletonObject4 instance ;

    private SingletonObject4() {

    }

    public static SingletonObject4 getInstance() {
        if (null == instance) {
            synchronized (SingletonObject4.class) {
                if (null == instance) {
                    instance = new SingletonObject4();
                }
            }
        }
        return SingletonObject4.instance;
    }
}
