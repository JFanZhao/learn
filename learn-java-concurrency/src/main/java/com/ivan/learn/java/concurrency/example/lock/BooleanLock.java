package com.ivan.learn.java.concurrency.example.lock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

/**
 * 布尔类型的锁
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-14 10:00
 **/
public class BooleanLock implements Lock{
    /**
     * 为true时，说明锁被占用
     * 为false时，锁空闲
     */

    private boolean initValue ;
    private Collection<Thread> blockedThreadCollection = new ArrayList<>();
    Thread currentThread ;

    public BooleanLock() {
        this.initValue = false;

    }

    @Override
    public void lock() throws InterruptedException {
        while (initValue) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.initValue = true;
        this.currentThread = Thread.currentThread();
    }

    @Override
    public void lock(long mills) throws InterruptedException, TimeoutException {
        if (mills <= 0) {
            lock();
        }
        long hasRemaining = mills;
        long end = System.currentTimeMillis() + mills;
        while (initValue) {
            if (hasRemaining <= 0) {
                throw new TimeoutException("超时了");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            hasRemaining = end - System.currentTimeMillis();
        }

        this.initValue = true;
        blockedThreadCollection.remove(Thread.currentThread());
        this.currentThread = Thread.currentThread();
    }

    @Override
    public void unlock() {
        if (currentThread == Thread.currentThread()) {
            this.initValue = false;
            Optional.of(Thread.currentThread().getName() + " release the lock monitor.")
                    .ifPresent(System.out::println);
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }

}
