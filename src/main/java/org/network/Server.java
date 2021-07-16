package org.network;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/7/16 7:16
 * @Description:
 */
public class Server {

    public static void main(String[] argv) throws IOException {
        ServerSocket serverSocket = new ServerSocket();
        InetSocketAddress addr = new InetSocketAddress(3001);
        serverSocket.bind(addr);
        List<Socket> list = new LinkedList<>();
        while (true) {
            Socket client = serverSocket.accept();
            list.add(client);
            System.out.println(list.size());
        }
    }
}
