package com.ivan.learn.java.concurrency.example.lock;

import java.util.Collection;

/**
 * 自定义显式锁接口
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-14 09:17
 * @see
 **/
public interface Lock {

    /**
     * 超时异常
     */
    class TimeoutException extends RuntimeException {
        public TimeoutException(String message) {
            super(message);
        }
    }

    void lock() throws InterruptedException;

    /**
     * 指定时间内拿不到锁，抛出异常
     * @param mills
     * @throws InterruptedException
     * @throws TimeoutException
     */
    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    /**
     * 获取阻塞中的线程
     * @return
     */
    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
