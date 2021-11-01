package com.ivan.learn.java.concurrency.example.pc;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 21:14
 **/
public class ProduceConsumerVersion1 {
    private int i = 0;

    final private Object LOCK = new Object();

    public void pruduce() {
        synchronized (LOCK) {
            System.out.println("P->" + (i++));
        }
    }

    public void consume() {
        synchronized (LOCK) {
            System.out.println("C->"+(i));
        }
    }

    public static void main(String[] args) {
        ProduceConsumerVersion1 produceConsumerVersion1 = new ProduceConsumerVersion1();

        new Thread(()->{
            while (true) {
                produceConsumerVersion1.pruduce();
            }
        },"P").start();

        new Thread(()->{
            while (true) {
                produceConsumerVersion1.consume();
            }
        },"C").start();
    }
}
