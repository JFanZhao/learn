package com.ivan.learn.java.concurrency.example.singleton;

/**
 * 最简单的单例，静态类，但是无法懒加载，不使用时会造成资源浪费
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:34
 **/
public class SingletonObject1 {

    private final static SingletonObject1 instance = new SingletonObject1();

    private SingletonObject1() {

    }

    public static SingletonObject1 getInstance() {
        return instance;
    }
}
