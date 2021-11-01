package com.ivan.learn.java.concurrency.example.threadlocal.context;

import java.util.stream.IntStream;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 22:33
 **/
public class ContextTest {
    public static void main(String[] args) {
        IntStream.range(1, 5)
                .forEach(i ->
                        new Thread(new ExecutionTask()).start()
                );
    }
}
