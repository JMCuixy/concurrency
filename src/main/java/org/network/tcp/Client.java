package org.network.tcp;

import java.io.IOException;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/7/16 7:19
 * @Description:
 */
public class Client {
    public static void main(String[] argv) throws IOException, InterruptedException {
        List<Socket> clients = new LinkedList<>();
        for (int i = 0; i < 1000000; i++) {
            Socket client = new Socket("127.0.0.1", 3001);
            clients.add(client);
            System.out.println("目前第" + i + "个 Socket 接入");
            Thread.sleep(10);
        }
    }
}
