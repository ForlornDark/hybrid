package com.lfm.test.cache;

import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RBucket;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

@Slf4j
public class RedissonTest {
    public static void main(String[] args) {

        RedissonTest test = new RedissonTest();
        Config config = test.config();
        RedissonClient redissonClient = Redisson.create(config);
        long c = System.currentTimeMillis();
        for (int i =0 ;i < 10;i++){
            RMap<Object, Object> ds = redissonClient.getMap("map");
            log.info("isExists;"+ds.isExists());
            ds.put("name","liu");
            ds.put("mail","ds@qq.com");
            log.info("value:{}",ds.get("name"));
        }
        log.info("time:{}",System.currentTimeMillis()-c);
        redissonClient.shutdown();
    }

    public Config config(){
        // 1. Create config object
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        // or read config from file
//        config = Config.fromYAML(new File("config-file.yaml"));
        return config;
    }


}
