package org.concurrency.demo.map;

import java.util.*;

/**
 * @author : cuixiuyin
 * @date : 2019/8/27
 */
public class SimpleHashMap extends AbstractMap {

    /**
     * 定一个初始大小的哈希表容量
     */
    private static final int SZ = 3;
    /**
     * 建一个hash数组，用linkedList实现
     */
    private LinkedList[] linkedLists = new LinkedList[SZ];

    @Override
    public Object put(Object key, Object value) {
        Object result = null;
        //对key的值做求模法求出index
        int index = key.hashCode() % SZ;
        if (index < 0) {
            index = -index;
        }
        //如果这个index位置没有对象，就新建一个
        if (linkedLists[index] == null) {
            linkedLists[index] = new LinkedList();
        }
        //取出这个index的对象linkedList
        LinkedList linkedList = linkedLists[index];
        //新建要存储的对象mPair
        MPair mPair = new MPair(key, value);
        ListIterator listIterator = linkedList.listIterator();
        boolean found = false;
        //遍历这个index位置的List,如果查找到跟之前一样的对象(根据equals来比较)，则更新那个key对应的value
        while (listIterator.hasNext()) {
            MPair next = (MPair) listIterator.next();
            if (mPair.equals(next)) {
                result = next.getValue();
                //更新动作
                listIterator.set(mPair);
                found = true;
                break;
            }
        }
        //如果没有找到这个对象，则在这index的List对象上新增一个元素。
        if (!found) {
            linkedLists[index].add(mPair);
        }
        return result;

    }

    @Override
    public Object get(Object key) {
        int index = key.hashCode() % SZ;
        if (index < 0) {
            index = -index;
        }
        if (linkedLists[index] == null) {
            return null;
        }
        LinkedList linkedList = linkedLists[index];
        // 新建一个空的对象值，因为equals()的比较是看他们的key是否相等，而在List中的遍历对象的时候，是通过key来查找对象的。
        MPair mPair = new MPair(key, null);
        ListIterator listIterator = linkedList.listIterator();
        while (listIterator.hasNext()) {
            MPair next = (MPair) listIterator.next();
            // 找到了这个key就返回这个value
            if (next.equals(mPair)) {
                return next.getValue();
            }
        }
        return null;

    }

    @Override
    public Set<Entry> entrySet() {
        Set set = new HashSet();
        for (int i = 0; i < linkedLists.length; i++) {
            if (linkedLists[i] == null) {
                continue;
            }
            Iterator iterator = linkedLists[i].iterator();
            while (iterator.hasNext()) {
                set.add(iterator.next());
            }
        }
        return set;
    }

    public static void main(String[] args) {
        SimpleHashMap simpleHashMap = new SimpleHashMap();
        simpleHashMap.put("1", "1");
        simpleHashMap.put("2", "2");
        simpleHashMap.put("3", "3");
        //这里有四个元素，其中key是1和key是4的index是一样的，所以index为1的List上面存了两个元素。
        simpleHashMap.put("4", "4");
        System.out.println(simpleHashMap);
        Object o = simpleHashMap.get("1");
        System.out.println(o);
        Object o1 = simpleHashMap.get("4");
        System.out.println(o1);
    }
}
