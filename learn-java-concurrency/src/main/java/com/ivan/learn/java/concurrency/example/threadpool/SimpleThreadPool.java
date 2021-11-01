package com.ivan.learn.java.concurrency.example.threadpool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * 简单线程池
 * 线程池需要包含的元素
 * 执行任务的线程队列：Thread queue，队列的大小是可以根据待运行的任务的个数动态调整的
 * 默认初始化最小值，有活跃的active，还有最大值；调整线程队列的大小时需要注意，需要等任务执行完成后
 * 待执行的任务：task queue
 * 拒绝策略：当线程队列满了的时候，再提交任务需要执行拒绝策略，例如：抛出异常，丢弃，阻塞，临时队列
 *
 * @author: ivan
 * @email: JFan.Jack@gmail.com
 * @created: 2021−09-14 22:12
 **/
public class SimpleThreadPool extends Thread {
    /**
     * 线程当前大小
     */
    private int size;
    /**
     * 待运行任务队列大小
     */
    private int queueSize;

    /**
     * 默认待运行队列大小
     */
    private final static int DEFAULT_TASK_QUEUE_SIZE = 2000;
    private static volatile int seq = 0;
    private final static String THREAD_PREFIX = "SIMPLE_THREAD_POOL-";
    private final static ThreadGroup group = new ThreadGroup("Pool_Group");

    /**
     * 待运行任务队列
     */
    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    /**
     * 线程队列
     */
    private final static List<WorkerTask> THREAD_QUEUE = new ArrayList<>();
    /**
     * 拒绝策略
     */
    private final DiscardPolicy discardPolicy;
    private final static DiscardPolicy DEFAULT_DISCARD_POLICY = () -> {
        throw new DiscardException("Discard This Task.");
    };

    private volatile boolean destory = false;

    private int min;
    private int active;
    private int max;

    public SimpleThreadPool() {
        this(4, 8, 12, DEFAULT_TASK_QUEUE_SIZE, DEFAULT_DISCARD_POLICY);
    }

    public SimpleThreadPool(int min, int active, int max, int queueSize, DiscardPolicy discardPolicy) {
        this.min = min;
        this.active = active;
        this.max = max;
        this.queueSize = queueSize;
        this.discardPolicy = discardPolicy;
        init();
    }

    private void init() {
        for (int i = 0; i < min; i++) {
            createTask();
        }
        this.size = min;
        this.start();
    }

    public void createTask() {
        WorkerTask task = new WorkerTask(group, THREAD_PREFIX + (seq++));
        task.start();
        THREAD_QUEUE.add(task);

    }

    /**
     * 提交任务
     * @param runnable
     */
    public void submit(Runnable runnable) {
        if (destory) {
            throw new IllegalStateException("The thread pool already destroy and not allow submit task.");
        }
        synchronized (TASK_QUEUE) {
            //执行拒绝策略
            if (TASK_QUEUE.size() > queueSize) {
                discardPolicy.discard();
            }
            TASK_QUEUE.addLast(runnable);
            TASK_QUEUE.notifyAll();
        }
        
    }

    public void shutdown() throws InterruptedException {
        //待运行任务不为空的时候，等待
        while (!TASK_QUEUE.isEmpty()) {
            Thread.sleep(50);
        }
        synchronized (THREAD_QUEUE) {
            int initVal = THREAD_QUEUE.size();
            while (initVal > 0) {
                for (WorkerTask task :
                        THREAD_QUEUE) {
                    if (task.taskState == TaskState.BLOCKED) {
                        task.close();
                        task.interrupt();
                        initVal--;
                    }else {
                        Thread.sleep(50);
                    }
                }
            }

            System.out.println(group.activeCount());

            this.destory = true;
            System.out.println("The thread pool disposed.");

        }
    }
    @Override
    public void run() {
        while (!destory) {
            System.out.printf("Pool#Min:%d,Active:%d,Max:%d,Current:%d,QueueSize:%d\n",
                    this.min, this.active, this.max, this.size, TASK_QUEUE.size());
            try {
                Thread.sleep(5_000L);
                /**
                 * 拓展线程池的容量
                 * 如果待执行任务队列大于活跃的线程数，并且当前大小小于活跃线程数，把线程数扩展active-size个
                 * 如果待执行任务大于线程最大个数，且当前大小小于最大线程数，线程数扩展到max
                 */
                if (TASK_QUEUE.size() > active && size < active) {
                    for (int i = size; i < active; i++) {
                        createTask();
                    }
                    System.out.println("The pool incremented to active.");
                    size = active;
                } else if (TASK_QUEUE.size() > max && size < max) {
                    for (int i = size; i < max; i++) {
                        createTask();
                    }
                    System.out.println("The pool incremented to max.");
                    size = max;
                }

                /**
                 * 如果待执行任务较少，空闲线程多，减少线程池线程数
                 */
                synchronized (THREAD_QUEUE) {
                    if (TASK_QUEUE.isEmpty() && size > active) {
                        System.out.println("=========Reduce========");
                        int releaseSize = size - active;
                        for (Iterator<WorkerTask> it = THREAD_QUEUE.iterator(); it.hasNext();){
                            if (releaseSize == 0) {
                                break;
                            }
                            WorkerTask next = it.next();
                            next.close();
                            next.interrupt();
                            it.remove();
                            releaseSize--;
                        }
                        size = active;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public int getSize() {
        return size;
    }

    public int getQueueSize() {
        return queueSize;
    }

    public static int getSeq() {
        return seq;
    }

    public int getMin() {
        return min;
    }

    public int getActive() {
        return active;
    }

    public int getMax() {
        return max;
    }

    private enum TaskState {
        FREE, RUNNING, BLOCKED, DEAD
    }

    private static class WorkerTask extends Thread {

        private volatile TaskState taskState = TaskState.FREE;

        public WorkerTask(ThreadGroup group, String name) {
            super(group, name);
        }

        public TaskState getTaskState() {
            return taskState;
        }

        @Override
        public void run() {
            OUTER:
            while (this.taskState != TaskState.DEAD) {
                Runnable runnable;
                synchronized (TASK_QUEUE) {
                    while (TASK_QUEUE.isEmpty()) {
                        taskState = TaskState.BLOCKED;
                        try {
                            TASK_QUEUE.wait();
                        } catch (InterruptedException e) {
                            System.out.println("Closed.");
                            break OUTER;
                        }
                    }
                    runnable = TASK_QUEUE.removeFirst();
                    if (runnable != null) {
                        taskState = TaskState.RUNNING;
                        runnable.run();
                        taskState = TaskState.FREE;
                    }
                }
            }
        }

        public void close() {
            this.taskState = TaskState.DEAD;
        }
    }

    /**
     * 拒绝异常类
     */
    public static class DiscardException extends RuntimeException {
        public DiscardException(String message) {
            super(message);
        }
    }

    public interface DiscardPolicy {
        void discard() throws DiscardException;
    }

    public static void main(String[] args) throws InterruptedException {
//        SimpleThreadPool threadPool = new SimpleThreadPool();
//        for (int i = 0; i < 40; i++) {
//            threadPool.submit(() -> {
//                System.out.println("The runnable  be serviced by " + Thread.currentThread() + " start.");
//                try {
//                    Thread.sleep(3000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("The runnable be serviced by " + Thread.currentThread() + " finished.");
//            });
//        }
//
//        Thread.sleep(10000);
//        threadPool.shutdown();
        IntStream.range(0,141).forEach(i-> System.out.print("${"+i+"},"));
    }
}
