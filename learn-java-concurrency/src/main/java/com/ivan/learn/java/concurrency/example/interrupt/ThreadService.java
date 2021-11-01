package com.ivan.learn.java.concurrency.example.interrupt;

/**
 * TODO
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-13 20:46
 **/
public class ThreadService {
    private Thread executeThread ;
    private boolean finished = false;

    public void execute(Runnable task) {
        executeThread = new Thread(()->{
            Thread runner = new Thread(task);
            runner.setDaemon(true);
            runner.start();

            try {
                runner.join();
                finished = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        executeThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if ((System.currentTimeMillis() - currentTime) >= mills) {
                System.out.println("超时了，中断他");
                executeThread.interrupt();
                break;
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                System.out.println("被中断");
                break;
            }

        }
        finished = false;
    }

}
