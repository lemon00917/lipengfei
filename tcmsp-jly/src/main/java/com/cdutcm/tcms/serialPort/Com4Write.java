package com.cdutcm.tcms.serialPort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import com.cdutcm.core.util.DateUtil;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;


/**
 * Com21PollingListener类使用“轮训”的方法监听串口COM21，
 * 并通过COM21的输入流对象来获取该端口接收到的数据（在本文中数据来自串口COM11）。
 */
public class Com4Write {
	 private static String hexString = "0123456789ABCDEF";
	 private static final char[] HEX_CHAR = {'0', '1', '2', '3', '4', '5', 
	            '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
	//转成16进制数据
		public static String encode(byte[] str) {
	        // 根据默认编码获取字节数组
	        byte[] bytes = str;
	        StringBuilder sb = new StringBuilder(bytes.length * 2);
	        // 将字节数组中每个字节拆解成2位16进制整数
	        for (int i = 0; i < bytes.length; i++) {
	            sb.append(hexString.charAt((bytes[i] & 0xf0) >> 4));
	            sb.append(hexString.charAt((bytes[i] & 0x0f) >> 0));
	        }
	        return sb.toString();
	    }
		
		
    public static void main(String[] args) throws IOException{
        //1.定义变量
        CommPortIdentifier com21 = null;//未打卡的端口
        SerialPort serialCom21 = null;//打开的端口
        InputStream inputStream = null;//端口输入流
        String  fileName=DateUtil.format(new Date(),"yyyyMMddHHmmss")+".txt";//新的文件名
   	 	String path="e:/jly";
   	 	String filePath=path+"/"+fileName; //文件路径
   	 	File file1 =new File(filePath); 
   	 	if(!file1 .exists()  && !file1 .isDirectory()){       
   	 		file1.createNewFile();  
		 	}
   	 	
   	
   	 	OutputStreamWriter  write  = new OutputStreamWriter (new FileOutputStream(file1),"UTF-8");
   	 	BufferedWriter writer=new BufferedWriter(write); 
        try{
            //2.获取并打开串口COM21
            com21 = CommPortIdentifier.getPortIdentifier("COM6");
            serialCom21 = (SerialPort) com21.open("Com21Listener", 1000);
            //3.获取串口的输入流对象
            inputStream = serialCom21.getInputStream();           
            //4.从串口读入数据
            //定义用于缓存读入数据的数组
            byte[] cache = new byte[24];
            //记录已经到达串口COM21且未被读取的数据的字节（Byte）数。
            int availableBytes = 0;
            int len=0;
            //无限循环，每隔20毫秒对串口COM21进行一次扫描，检查是否有数据到达
            while(true){
                //获取串口COM21收到的可用字节数
                availableBytes = inputStream.available();
                //如果可用字节数大于零则开始循环并获取数据
             
                    //从串口的输入流对象中读入数据并将数据存放到缓存数组中
                 
//                    System.out.println(Inputstr2Str_byteArr(inputStream, "utf-8"));
                    //将获取到的数据进行转码并输出
                    while ((len = inputStream.read(cache)) == 23) {
//                      while(availableBytes > 0){
                          //从串口的输入流对象中读入数据并将数据存放到缓存数组中
                      	for (int i = 0; i < len; i++) {
                      		System.out.println(cache);
                      
                      	}
                    
                    for(int j = 0;j < cache.length && j < availableBytes; j++){
                         //因为COM11口发送的是使用byte数组表示的字符串，
                        //所以在此将接收到的每个字节的数据都强制装换为char对象即可，
                        //这是一个简单的编码转换，读者可以根据需要进行更加复杂的编码转换。
//                    	String encoded = Base64.getEncoder().encodeToString(cache);
                    	writer.write(cache[j]+",");                 
                        System.out.print(cache[j]+",");                       
                    }
                    writer.write("\r\n");
                    System.out.println();
                    writer.flush();                   
                    //更新循环条件
                    availableBytes = inputStream.available();
                }                
                //让线程睡眠20毫秒
                Thread.sleep(20);             
            } 
           
        }catch(InterruptedException e){
            e.printStackTrace();
        }catch (NoSuchPortException e) {
            //找不到串口的情况下抛出该异常
            e.printStackTrace();
        } catch (PortInUseException e) {
            //如果因为端口被占用而导致打开失败，则抛出该异常
            e.printStackTrace();
        } catch (IOException e) {
            //如果获取输出流失败，则抛出该异常
            e.printStackTrace();
        }catch(Exception e){
        	
   		 e.printStackTrace();
   	 }
    }
    
    public static void writeTotxt(String str){
    	 String  fileName=DateUtil.format(new Date(),"yyyyMMddHHmmss")+".txt";//新的文件名
    	 String path="e:/jly";
    	 String filePath=path+"/"+fileName;
    	 try{
    	 File file1 =new File(filePath); 
    	 if(!file1 .exists()  && !file1 .isDirectory()){       
    		  file1.createNewFile();  
    		 	}
    	 System.out.println(path+"/"+fileName);
    	 OutputStreamWriter  write  = new OutputStreamWriter (new FileOutputStream(file1),"UTF-8");
    	  BufferedWriter writer=new BufferedWriter(write); 	   		
    		 writer.write(str);
    		 writer.flush();
    		 writer.close();
    	 }catch(Exception e){
    		 e.printStackTrace();
    	 }		
    	}
    }
