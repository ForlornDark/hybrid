package com.lfm.test.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.ReentrantLock;

public class SynchronizedTest {

    public static void main(String[] args) {
//        new Thread(SynchronizedTest::methodD).start();
//        new Thread(SynchronizedTest::methodE).start();
//        testThreadJoin();

//        testCyclicBarrier();
        testReentrantLock();

    }


    private static final Object o = new Object();
    private static int i = 0;
    private synchronized static  void methodA(){

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method A");
        i++;
        if (i > 5){
            return;
        }
        methodB();

    }
    private static synchronized void methodB(){

        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method B");
        i++;
        if (i > 5){
            return;
        }
        methodA();
    }

    private  static void methodC(){

        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("method C");
    }

    private  static void methodD(){
        synchronized (o){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method D");
            i++;
            if (i > 5){
                return;
            }
            methodD();
        }


    }
    private  static void methodE(){
        synchronized (o){
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("method E");
            methodD();
        }
    }

    /**
     * thread join :等到thread 和thread1 执行完毕执行thread2
     */
    private static void testThreadJoin(){
        Thread thread = new Thread(()-> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread ");});
        Thread thread1 = new Thread(()-> {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread 1");});

        Thread thread2= new Thread(()-> {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("thread2 ");});
        thread1.start();
        thread.start();
        try {
            thread.join();
            thread1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
    }

    static class BarrierRunnable implements Runnable{
        private CyclicBarrier barrier;
        public BarrierRunnable(CyclicBarrier barrier){
            this.barrier = barrier;
        }

        @Override
        public void run() {
            long sleep  =(long) (Math.random() * 1000);
            try {
                Thread.sleep(sleep);
                System.out.println("sleep"+sleep);
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    private static void testCyclicBarrier(){
        int N = 4;
        int t = 8;

        CyclicBarrier barrier = new CyclicBarrier(N,()->{
            System.out.println("---------------------");
        });
        for (int i =0;i<t;i++){
            new Thread(new BarrierRunnable(barrier)).start();
        }
    }




    private static void testReentrantLock(){
        ReentrantLock lock = new ReentrantLock();

        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("thread 1");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            lock.lock();
            try {
                Thread.sleep(10);
                System.out.println("thread 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }).start();
    }
}
