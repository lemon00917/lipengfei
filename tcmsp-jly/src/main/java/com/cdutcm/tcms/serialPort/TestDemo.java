package com.cdutcm.tcms.serialPort;

public class TestDemo {

	 public static void main(String[] args) {
		 
	        Thread thread=new Thread(new SerialPortTest1());
	        thread.start();
	    }
	 
}
