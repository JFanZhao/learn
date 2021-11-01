package com.ivan.learn.java.concurrency.example.pc;

import java.util.stream.Stream;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 21:37
 **/
public class ProduceConsumerVersion {
    private int i = 0;

    final Object LOCK = new Object();

    private volatile boolean isProduced = false;

    public void produce() {
        synchronized (LOCK) {
            //已有生产，循环wait
            while (isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            //生产数据
            i++;
            System.out.println("P->" + i);
            //唤醒消费者消费
            LOCK.notifyAll();
            isProduced = true;
        }
    }

    public void consume() {
        synchronized (LOCK) {
            //没有消费是数据，等待，这里注意，不能用if，否则会出现重复消费的问题
            while (!isProduced) {
                try {
                    LOCK.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("C->" + i);
            LOCK.notifyAll();
            isProduced = false;
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion pc = new ProduceConsumerVersion();

        Stream.of("P1", "P2", "P3").forEach(n->{
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.produce();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });

        Stream.of("C1", "C2", "C3", "C4").forEach(n->{
            new Thread(n) {
                @Override
                public void run() {
                    while (true) {
                        pc.consume();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }.start();
        });
    }
}
