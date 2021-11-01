package com.ivan.learn.java.concurrency.example.rwlock;

/**
 * 读写锁
 * 读读不需要串行化
 * 读写需要
 * 写读需要
 * 写写需要
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−10-04 19:32
 **/
public class ReadWriteLock {
    /**
     * 正在读的线程的个数
     */
    private int readingReaders = 0;
    /**
     * 等待读的线层的个数
     */
    private int waitingReaders = 0;
    /**
     * 正在写的线程的个数
     */
    private int writingWriters = 0;
    /**
     * 等待写的线程的个数
     */
    private int waitingWriters = 0;


    private boolean preferWriter = true;

    public ReadWriteLock(boolean preferWriter) {
        this.preferWriter = preferWriter;
    }

    public ReadWriteLock() {
        this(true);
    }

    public synchronized void readLock() throws InterruptedException{
        this.waitingReaders++;
        try {
            while (waitingWriters > 0 || (preferWriter && waitingWriters > 0)) {
                this.wait();
            }
            this.readingReaders++;
        }finally {
            this.waitingReaders--;
        }
    }

    public synchronized void readUnlock() {
        this.readingReaders--;
        this.notifyAll();
    }

    public synchronized void writeLock() throws InterruptedException {
        this.waitingWriters++;
        try {
            while (readingReaders > 0 || writingWriters > 0) {
                this.wait();
            }
            this.writingWriters++;
        } finally {
            this.waitingWriters--;
        }
    }

    public synchronized void writeUnlock() {
        this.writingWriters--;
        this.notifyAll();
    }


}
