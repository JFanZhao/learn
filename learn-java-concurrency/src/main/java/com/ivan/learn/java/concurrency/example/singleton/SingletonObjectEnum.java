package com.ivan.learn.java.concurrency.example.singleton;

import java.util.stream.IntStream;

/**
 * 枚举实现
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-26 22:50
 **/
public class SingletonObjectEnum {
    private SingletonObjectEnum() {

    }

    private enum Singleton {
        INSTANCE;

        private final SingletonObjectEnum instance;

        Singleton() {
            instance = new SingletonObjectEnum();
        }

        public SingletonObjectEnum getInstance() {
            return instance;
        }
    }

    public static SingletonObjectEnum getInstance() {
        return Singleton.INSTANCE.getInstance();
    }

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100)
                .forEach(i -> new Thread(String.valueOf(i)) {
                    @Override
                    public void run() {
                        System.out.println(SingletonObjectEnum.getInstance());
                    }
                }.start());
    }
}
