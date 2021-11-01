package com.ivan.learn.java.concurrency.example.singleton;

/**
 * holder方式实现
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:48
 **/
public class SingletonObjectHolder {
    private SingletonObjectHolder() {

    }

    private static class InstanceHolder{
        private final static SingletonObjectHolder instnce = new SingletonObjectHolder();
    }

    public static SingletonObjectHolder getInstance() {
        return InstanceHolder.instnce;
    }
}
