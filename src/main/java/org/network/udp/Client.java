package org.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/7/20 20:30
 * @Description:
 */
public class Client {

    public static void main(String[] args) {
        DatagramSocket client = null;
        try {
            client = new DatagramSocket();
            //创建数据报
            byte[] buffer = "hello,world".getBytes();
            InetAddress byName = InetAddress.getByName("192.168.1.23");
            //建立将要传输的UDP包，并指定ip地址和端口号
            DatagramPacket datagramPacket = new DatagramPacket(buffer, buffer.length, byName, 8088);
            client.send(datagramPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            client.close();
        }
    }
}
