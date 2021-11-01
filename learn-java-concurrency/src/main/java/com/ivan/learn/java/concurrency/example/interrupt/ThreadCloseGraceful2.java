package com.ivan.learn.java.concurrency.example.interrupt;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 21:04
 **/
public class ThreadCloseGraceful2 {

    private static class Worker extends Thread {
        @Override
        public void run() {
            while (true) {
                if (Thread.interrupted()) {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Worker worker = new Worker();
        worker.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        worker.interrupt();
    }

}
