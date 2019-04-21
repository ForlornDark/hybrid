package com.lfm.test.concurrent;


import java.util.Random;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class CompletableFutureTest {

    public static void main(String[] args){
        runSameTime();
    }
    
    public static void runSameTime(){
        CompletableFuture[] futures = new CompletableFuture[3];
        futures[0] = CompletableFuture.supplyAsync(new TT());
        futures[1] = CompletableFuture.supplyAsync(new TT2());
        futures[2] = CompletableFuture.supplyAsync(new TT());
        try {
            long start = System.currentTimeMillis();
            CompletableFuture.allOf(futures).get();
            System.out.println( "during："+(System.currentTimeMillis() -start));
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

    }

    static class TT implements Supplier<Long>{

        @Override
        public Long get() {
            long i = new Random().nextInt(10000);
            System.out.println("sleeping:"+i+",currentThread:"+Thread.currentThread().getId());
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return i;
        }
    }

    static class TT2 implements Supplier<Double>{

        @Override
        public Double get() {
            long i = new Random().nextInt(10000);
            System.out.println("sleeping:"+i+",currentThread:"+Thread.currentThread().getId());
            try {
                Thread.sleep(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 1.0d * i;
        }
    }
}
