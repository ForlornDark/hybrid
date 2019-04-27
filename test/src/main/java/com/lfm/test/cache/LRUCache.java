package com.lfm.test.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
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
        LRUCache2<Integer,String> cache = new LRUCache2<>(100);
        for (int i =0 ;i < 500;i++){
            int i1 = new Random().nextInt(100);
            if (cache.get(i1) == null){
                cache.put(i1,String.valueOf(i1));
            }
            log.info(i1+",cache size:"+cache.getSize());
            log.info(cache.print());
        }
    }
}
