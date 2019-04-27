package com.lfm.test.cache;

import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
@Slf4j
public class LRUCache2<K,V> {
//    private int currentCacheSize=0;
    private int size;
    private HashMap<K,CacheNode<K,V>> caches;
    private CacheNode<K,V> first;
    private CacheNode<K,V> last;

    public LRUCache2(int size){
        this.size = size;
        caches = new HashMap<>(size);
    }

    public int getSize(){
        return caches.size();
    }

    public void put(K k,V v){
        CacheNode<K,V> node = caches.get(k);
        if(node == null){
            if(caches.size() >= size){
                caches.remove(last.key);
                removeLast();
            }
            node = new CacheNode<>();
            node.key = k;
        }
        node.value = v;
        moveToFirst(node);
        caches.put(k, node);
    }

    public V get(K k){
        CacheNode<K,V> node = caches.get(k);
        if(node == null){
            return null;
        }
        log.info("hit cache");
        moveToFirst(node);
        return node.value;
    }

    public CacheNode remove(K k){
        CacheNode<K,V> node = caches.get(k);
        if(node != null){
            if(node.pre != null){
                node.pre.next=node.next;
            }
            if(node.next != null){
                node.next.pre=node.pre;
            }
            if(node == first){
                first = node.next;
            }
            if(node == last){
                last = node.pre;
            }
        }

        return caches.remove(k);
    }

    public void clear(){
        first = null;
        last = null;
        caches.clear();
    }



    private void  moveToFirst(CacheNode<K,V> node){
        if(first == node){
            return;
        }
        if(node.next != null){
            node.next.pre = node.pre;
        }
        if(node.pre != null){
            node.pre.next = node.next;
        }
        if(node == last){
            last= last.pre;
        }
        if(first == null || last == null){
            first = last = node;
            return;
        }

        node.next=first;
        first.pre = node;
        first = node;
        first.pre=null;

    }

    private void removeLast(){
        if(last != null){
            last = last.pre;
            if(last == null){
                first = null;
            }else{
                last.next = null;
            }
        }
    }

    public String print(){
        StringBuilder sb = new StringBuilder();
        CacheNode<K,V> node = first;
        while(node != null){
            sb.append(node.value);
            sb.append("-");
            node = node.next;
        }

        return sb.toString();
    }

    class CacheNode<K,V>{
        CacheNode<K,V> pre;
        CacheNode<K,V> next;
        K key;
        V value;
        public CacheNode(){

        }
    }
}
