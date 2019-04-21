package com.lfm.test.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorTest {


    public static void main(String[] args){
        ArrayBlockingQueue<Runnable> queue = new ArrayBlockingQueue<>(100);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 6, 1000, TimeUnit.SECONDS, queue);
        ExecutorTest executorTest = new ExecutorTest();
        for (int i = 0;i<100;i++){
            executor.execute(executorTest::add);
            System.out.println("size:"+executor.getQueue().size());
        }
        executor.execute(executorTest::add);
    }

    private final Object object = new Object();
    private int i = 0;
    private void add(){
        i++;
        System.out.println(i);
    }

    public void countDownLatch() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1000);
        for (int i =0;i < 5;i++){
            new Thread(new MyRunnable(latch)).start();
        }
        latch.await();
    }
    class MyRunnable implements Runnable{
        CountDownLatch latch;

        public MyRunnable(CountDownLatch latch){
           this.latch = latch;

        }
        @Override
        public void run() {
            while (latch.getCount() > 0){
                latch.countDown();
                System.out.println("latch:"+latch.getCount());
            }
        }
    }



    public void testThreadLocal() throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        for (int i  =0;i < 5;i++){
            Thread t = new Thread(() -> {
                for (int j = 0 ;j < 10000;j++){
                    String name = Thread.currentThread().getName();
                    threadLocal.set(name+"thread:"+j);
                    String s = threadLocal.get();
                    System.out.println(s.startsWith(name));
                }
            },""+i);
            t.start();
        }
        Thread.sleep(10000);
    }
}


