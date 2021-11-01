package com.ivan.learn.java.concurrency.example.threadlocal;

import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 22:03
 **/
public class ThreadLocalSimulator<T>{
    private Map<Thread, T> storage = new HashMap<>();

    public void set(T t) {
        synchronized (this) {
            Thread key = Thread.currentThread();
            storage.put(key,t);
        }
    }

    public T get(){
        synchronized (this) {
            Thread key = Thread.currentThread();
            T value = storage.get(key);
            if (null == value) {
                return  initialValue();
            }

            return value;
        }
    }

    public T initialValue() {
        return null;
    }

}
