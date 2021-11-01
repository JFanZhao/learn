package com.ivan.learn.java.concurrency.example.interrupt;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 20:55
 **/
public class ThreadCloseForce {
    public static void main(String[] args) {
        ThreadService service = new ThreadService();
        long start = System.currentTimeMillis();
        service.execute(()->{
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        service.shutdown(100);
        long end = System.currentTimeMillis();
        System.out.println(end - start );
    }
}
