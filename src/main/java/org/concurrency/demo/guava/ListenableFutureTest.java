package org.concurrency.demo.guava;

import com.google.common.util.concurrent.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xiuyin.cui@luckincoffee.com
 * @Date 2020-07-24 13:46
 * @Description ListenableFuture
 * 1. ListenableFuture 继承自 juc 的 Future 接口
 * 2. ListenableFuture 可以允许你注册回调方法(callbacks)，在运算（多线程执行）完成的时候进行调用,  或者在运算（多线程执行）完成后立即执行
 */
public class ListenableFutureTest {

    static ExecutorService executorService = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1);

    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("Hello ListenableFuture");
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(executorService);
        ListenableFuture<?> listenableFuture = listeningExecutorService.submit(runnable);
        Futures.addCallback(listenableFuture, new FutureCallback<Object>() {
            @Override
            public void onSuccess(Object result) {
                System.out.println("success");
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

}
