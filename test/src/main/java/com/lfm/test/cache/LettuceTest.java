package com.lfm.test.cache;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RLock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author liufuming
 * @date 2019/12/25 17:17
 **/
@Slf4j
public class LettuceTest {
    RedisClient redisClient;
    public LettuceTest(){
        redisClient = RedisClient.create("redis://123456@192.168.0.236:6379/0");
    }

    ExecutorService executor = Executors.newFixedThreadPool(10);
    public static void main(String[] args) {
        LettuceTest test = new LettuceTest();
        test.atomicLong();
        test.shutdown();
    }

    private void atomicLong(){
        initAtomicLong();
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        long c = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            executor.execute(()->{
                try{
                    StatefulRedisConnection<String, String> connect = redisClient.connect();
                    while (true){

                        try {
                            RedisCommands<String, String> sync = connect.sync();
                            String s = sync.get("product_100");
                            if (Long.parseLong(s) > 0){
                                Long decr = sync.decr("product_100");
                                log.info("stock-------------,{}",decr);
                            }else {
                                break;
                            }
                        }catch (Exception e){

                        }

                    }
                    connect.close();
                }catch (Exception e){
                    log.error("",e);
                }finally {
                    latch.countDown();
                    log.info("count============,{}",latch.getCount());
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("exec time = {}",System.currentTimeMillis() - c);
    }

    private void initAtomicLong(){
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> sync = connect.sync();
        sync.set("product_100","50000");
        connect.close();
    }

    public void shutdown(){
        redisClient.shutdown();
    }
}
