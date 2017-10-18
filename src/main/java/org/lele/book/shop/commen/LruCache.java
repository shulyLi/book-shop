package org.lele.book.shop.commen;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * author  shuly
 * date    17-8-18.
 */
public class LruCache<K, V> {
    private final Map<K, V> store;
    private final int SIZE;
    public LruCache(int size) {
        this.SIZE = size;
        this.store = new LinkedHashMap<K, V>() {
            private static final long serialVersionUID = -3834209229668463829L;
            @Override
            protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
                return size() > SIZE;
            }
        };
    }
    public void put(K key, V value){
        synchronized (store) {
            store.put(key, value);
        }
    }
    public V get(K key) {
        synchronized (store) {
            return store.get(key);
        }
    }
    public boolean contain(K key) {
        synchronized (store) {
            return store.containsKey(key);
        }
    }
    public void clean() {
        synchronized (store) {
            store.clear();
        }
    }
}
