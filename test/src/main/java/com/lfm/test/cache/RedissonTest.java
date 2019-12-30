package com.lfm.test.cache;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RAtomicLong;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.io.Closeable;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissonTest {
    private RedissonClient redissonClient;

    private BlockingQueue blockingQueue = new ArrayBlockingQueue(10);

//    ThreadPoolExecutor executor = new ThreadPoolExecutor(4,10,1,TimeUnit.MINUTES,blockingQueue);

    ExecutorService executor = Executors.newFixedThreadPool(10);
    public RedissonTest(){
        Config config = config();
        redissonClient = Redisson.create(config);
    }
    public static void main(String[] args) {

        RedissonTest test = new RedissonTest();
        test.atomicLong();
        test.close();

    }

    private Config config(){
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.0.236:6379")
                .setPassword("123456");
        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
        return config;
    }

    private void rMap(){
        long c = System.currentTimeMillis();
        for (int i =0 ;i < 10;i++){
            RMap<String, String> ds = redissonClient.getMap("map");
            log.info("isExists;"+ds.isExists());
            ds.put("name","liu");
            ds.put("mail","ds@qq.com");
            log.info("value:{}",ds.get("name"));
        }
        log.info("time:{}",System.currentTimeMillis()-c);
    }


    private void atomicLong(){
        initAtomicLong();
        int size = 10;
        CountDownLatch latch = new CountDownLatch(size);
        long c = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            executor.execute(()->{
                try{
                    while (true){
//                        RLock lock = redissonClient.getLock("product_lock_100");
                        try {
//                            lock.lock();
                            RAtomicLong atomicLong = redissonClient.getAtomicLong("product_100");
                            if (atomicLong.get() > 0){
                                long stock = atomicLong.decrementAndGet();
                                log.info("stock-------------,{}",stock);
                            }else {
                                break;
                            }
                        }catch (Exception e){
//                            lock.unlock();
                        }

                    }
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
        RAtomicLong atomicLong = redissonClient.getAtomicLong("product_100");
        atomicLong.set(50000);

    }


    private void close(){
        redissonClient.shutdown();
    }

}
