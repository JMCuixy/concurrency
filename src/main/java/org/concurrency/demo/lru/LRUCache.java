package org.concurrency.demo.lru;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

/**
 * @Author 1099442418@qq.com
 * @Date 2020/11/2 13:08
 * @Description 利用 LinkedHashMap<K,V> 实现 LRU 算法
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {

    private final int CACHE_SIZE;

    /**
     * 能缓存多少数据
     *
     * @param cacheSize 缓存的数据量
     */
    public LRUCache(int cacheSize) {
        // true 表示让 LinkedHashMap 按照访问顺序来进行排序，最近访问的放在头部，最老访问的放在尾部
        // false 表示按照插入顺序来进行排序
        super((int) Math.ceil(cacheSize / 0.75) + 1, 0.75f, true);
        CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Entry<K, V> eldest) {
        return size() > CACHE_SIZE;
    }
}
