package org.concurrency.demo.map;

import java.util.Map;

/**
 * Description:首先新建一个类作为map中存储的对象并重写了 hashCode()和 equals() 方法
 */
public class MPair implements Map.Entry, Comparable<MPair> {
    private Object key, value;

    public MPair(Object key, Object value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public int compareTo(MPair mPair) {
        return ((Comparable) key).compareTo(mPair.key);
    }

    @Override
    public Object getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public int hashCode() {
        int result = key != null ? key.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return key.equals(((MPair) o).key);
    }

    @Override
    public Object setValue(Object v) {
        Object result = value;
        this.value = v;
        return result;
    }

    @Override
    public String toString() {
        return "MPair{" + "key=" + key + ", value=" + value + '}';
    }
}
