package com.lfm.test.cache;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
public class LRUCache<K,V> {


    private int size = 100;

    private int count = 2;


    private MyLinkedHashMap<K,V> map;

    public LRUCache(int size){
        this.size = size;
        map = new MyLinkedHashMap<>(size);
    }

    private class MyLinkedHashMap<K,V> extends LinkedHashMap<K,V>{

        public MyLinkedHashMap(int size){
            super(size);
        }

        @Override
        protected boolean removeEldestEntry(Map.Entry eldest) {
            return size() > size;
        }

    }

    public synchronized V get(K k){
        V v = map.get(k);
        if (v != null){
            log.info("hit cache:");
        }
        return v;
    }
    public synchronized int getSize(){
        return map.size();
    }

    public synchronized V put(K k,V v){
        return map.put(k,v);
    }

    public String print() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<K, V> entry : map.entrySet()) {
           builder.append(entry.getValue());
           builder.append("-");
        }
        return builder.toString();
    }

    public static void main(String[] args){
        RateLimiter rateLimiter = RateLimiter.create(300);

    }
}
