package com.cdutcm.tcms.socket;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UdpServer {

	// 定义一些常量
    private final int MAX_LENGTH = 1024; // 最大接收字节长度
    private final int PORT_NUM   = 115200;   // port号
    // 用以存放接收数据的字节数组
    private byte[] receMsgs = new byte[MAX_LENGTH];
    // 数据报套接字
    private DatagramSocket datagramSocket;
    // 用以接收数据报
    private DatagramPacket datagramPacket;
   
    public UdpServer(){
        try {
			/******* 接收数据流程**/
            // 创建一个数据报套接字，并将其绑定到指定port上
            datagramSocket = new DatagramSocket(PORT_NUM);
            // DatagramPacket(byte buf[], int length),建立一个字节数组来接收UDP包
            datagramPacket = new DatagramPacket(receMsgs, receMsgs.length);
            // receive()来等待接收UDP数据报
            datagramSocket.receive(datagramPacket);
           
            /****** 解析数据报****/
            String receStr = new String(datagramPacket.getData(), 0 , datagramPacket.getLength());
            System.out.println("Server Rece:" + receStr);
            System.out.println("Server Port:" + datagramPacket.getPort());
           
            /***** 返回ACK消息数据报*/
            // 组装数据报
            byte[] buf = "WV000001TM010009FQ000010A1000001A2000000A3000000A4000002A5000001A6000000".getBytes();
            DatagramPacket sendPacket = new DatagramPacket(buf, buf.length, datagramPacket.getAddress(), datagramPacket.getPort());
            // 发送消息
            datagramSocket.send(sendPacket);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭socket
            if (datagramSocket != null) {
                datagramSocket.close();
            }
        }
    }
    
    public static void main(String[] args) {
    	final long timeInterval = 10000;// 1s运行一次
		Runnable runnable = new Runnable() {
			public void run() {
				while (true) {
					// ------- code for task to run
					try {
						System.out.println("-----10s运行一次-------");
						new UdpClient();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					// ------- ends here
					try {
						Thread.sleep(timeInterval);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
