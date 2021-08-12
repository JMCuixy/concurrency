package org.concurrency.demo.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.CacheStats;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListenableFutureTask;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020-07-25 14:50
 * @Description CacheBuilder
 * 1. CacheBuilder —— 最方便简单的内存缓存框架
 */
public class CacheBuilderTest {

    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder()
                .expireAfterWrite(60, TimeUnit.SECONDS)
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
                        return "Hello Guava Cache " + key + "load";
                    }

                    @Override
                    public ListenableFuture<String> reload(String key, String oldValue) throws Exception {
                        ListenableFutureTask<String> listenableFutureTask = ListenableFutureTask.create(() -> {
                            System.out.println("key:" + key + ", reload");
                            return "Hello Guava Cache " + key + "reload" + atomicInteger.incrementAndGet();
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
            Thread.sleep(65000);
            System.out.println("---------------------------------------------------------");
        }
    }
}
