package org.concurrency.demo.guava;

import com.google.common.cache.*;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020-07-25 14:50
 * @Description CacheBuilder
 * 1. CacheBuilder —— 最方便简单的内存缓存框架
 */
public class CacheBuilderTest {


    public static void main(String[] args) throws InterruptedException {

        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                // expireAfterAccess: 当缓存项在指定的时间段内没有被读或写就会被回收。
                // expireAfterWrite：当缓存项在指定的时间段内没有更新就会被回收。为了避免缓存雪崩，guava 会限制只有一个加载
                // 操作时进行加锁，其他请求必须阻塞等待这个加载操作完成。而且，在加载完成之后，其他请求的线程会逐一获得锁，
                // 去判断是否已被加载完成，每个线程必须轮流地走一个“获得锁，获得值，释放锁”的过程，这样性能会有一些损耗。
                // refreshAfterWrite：当缓存项上一次更新操作之后的多久会被刷新。在 refresh 的过程中，guava 会限制只有一个加
                // 载操作时进行加锁，而其他查询先返回旧值，这样能有效减少等待和锁争用，所以 refreshAfterWrite 会比 expireAfterWrite
                // 性能好。但它也有一个缺点，在吞吐量很低的情况下，如很长一段时间内没有查询之后，发生的查询有可能会得到一个旧值
                // （这个旧值可能来自于很长时间之前）
                .refreshAfterWrite(30, TimeUnit.SECONDS)
                .maximumSize(10)
                .removalListener(new RemovalListener() {
                    @Override
                    public void onRemoval(RemovalNotification notification) {
                        System.out.println("key:" + notification.getKey() + ", remove!");
                    }
                })
                .recordStats()
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {
                        System.out.println("key:" + key + ", load");
                        return "Hello Guava Cache " + key;
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
                            System.out.println("key:" + key + ", reload");
                            return "Hello Guava Cache " + key;
                        });
                        CompletableFuture.runAsync(listenableFutureTask);
                        return listenableFutureTask;
                    }
                });

        while (true) {
            String lucky = loadingCache.getUnchecked("lucky");
            System.out.println(lucky);
            CacheStats stats = loadingCache.stats();
            System.out.println("缓存命中率:" + stats.hitCount());
            System.out.println("加载新值的平均时间，单位为毫秒:" + stats.averageLoadPenalty() / 10000);
            System.out.println("缓存项被回收的总数，不包括显式清除:" + stats.evictionCount());
            Thread.sleep(60000);
            System.out.println("---------------------------------------------------------");
        }

    }
}
