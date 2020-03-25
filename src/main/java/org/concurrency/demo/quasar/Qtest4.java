package org.concurrency.demo.quasar;

import co.paralleluniverse.fibers.httpasyncclient.FiberCloseableHttpAsyncClient;
import co.paralleluniverse.fibers.httpclient.FiberHttpClientBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

/**
 * @author : cuixiuyin
 * @date : 2020/3/25
 */
public class Qtest4 {

    public static void main(String[] args) {
        final CloseableHttpClient client = FiberHttpClientBuilder.
                create(2). // use 2 io threads
                setMaxConnPerRoute(200).
                setMaxConnTotal(200).build();


        final CloseableHttpAsyncClient asyncClient = FiberCloseableHttpAsyncClient.wrap(HttpAsyncClients.
                custom().
                setMaxConnPerRoute(200).
                setMaxConnTotal(200).
                build());

    }
}
