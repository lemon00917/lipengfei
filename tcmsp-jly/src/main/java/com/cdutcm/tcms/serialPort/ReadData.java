package com.cdutcm.tcms.serialPort;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import com.cdutcm.core.util.DateUtil;
import com.cdutcm.tcms.ajy.model.Record;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;

public class ReadData {
	private InputStream inputStream = null; //端口输入流	
	private CommPortIdentifier com4 = null;//未打卡的端口
	private SerialPort serialCom4 = null;//打开的端口
	 private static String hexString = "0123456789ABCDEF";
	 List<String> listNames=new ArrayList<String>();
	//有参构造方法
		public ReadData(SerialPort serialCom4,CommPortIdentifier com4) throws NoSuchPortException, PortInUseException {	
	    //2.获取并打开串口COM4		
				this.com4 = CommPortIdentifier.getPortIdentifier("COM4");
				this.serialCom4 = (SerialPort)com4.open("Com4Listener", 1000);
		} 
	//无参构造方法
		public ReadData() throws NoSuchPortException, PortInUseException{
			this.com4 = CommPortIdentifier.getPortIdentifier("COM4");
			this.serialCom4 = (SerialPort)com4.open("Com4Listener", 1000);
		}
	
	//获取filepath
		public String getFilePath(){
			String fileName=DateUtil.format(new Date(),"yyyyMMddHHmmss")+".txt";//新的文件名
	   	 	String path="e:/jly";
	   	 	String filePath=path+"/"+fileName; //文件路径	 
			return filePath;
		}
		//获取Writer
	public BufferedWriter getWriter() throws NoSuchPortException, PortInUseException, IOException{				 
	   	 	String filePath=getFilePath(); //文件路径	   	 	
	   	 	File file1 =new File(filePath);
	   	 	if(!file1 .exists()  && !file1 .isDirectory()){       
	   	 		file1.createNewFile();  
			 	}
	   	 	OutputStreamWriter   write  = new OutputStreamWriter (new FileOutputStream(file1),"UTF-8");
	   	 	BufferedWriter writer=new BufferedWriter(write); 
		return writer;
	   	    
	}
	
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
	
	public int jcStart() throws IOException, NoSuchPortException, PortInUseException {			
	   	 	BufferedWriter writer=getWriter(); 
	        try{
	            //3.获取串口的输入流对象
	            inputStream = this.serialCom4.getInputStream();	            		        
	            //4.从串口读入数据
	            //定义用于缓存读入数据的数组
	            byte[] cache = new byte[1024];
	        
	            //记录已经到达串口COM21且未被读取的数据的字节（Byte）数。
	            int availableBytes = 0;
	            //无限循环，每隔20毫秒对串口COM21进行一次扫描，检查是否有数据到达
	            while(true){
	                //获取串口COM21收到的可用字节数
	                availableBytes = inputStream.available();
	                //如果可用字节数大于零则开始循环并获取数据
	                while(availableBytes > 0){
	                    //从串口的输入流对象中读入数据并将数据存放到缓存数组中
	                    inputStream.read(cache);
	                    
	                    //String hexStr=byte2HexString(cache);
	                    encode(cache);
	                    //将获取到的数据进行转码并输出
	                    for(int j = 0;j < cache.length && j < availableBytes; j++){
	                        //因为COM11口发送的是使用byte数组表示的字符串，
	                        //这是一个简单的编码转换，读者可以根据需要进行更加复杂的编码转换。
	                    	
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
	        }catch (IOException e) {
	            //如果获取输出流失败，则抛出该异常
	            e.printStackTrace();
	        }catch(Exception e){
	        	
	   		 e.printStackTrace();
	   	 }
			return 0;
		
	}
	
	
	//将获取的数据处理 获得平均值，再根据算法，得出体质等信息

	public ArrayList<Double> closePort(SerialPort serialPort) throws IOException, NoSuchPortException, PortInUseException{
		BufferedWriter writer=getWriter(); 
		 writer.flush(); 
		 writer.close();
		//读取文件
		 String filePath=getFilePath();
			
				System.out.println(filePath);
				ArrayList<Double> jc = new ArrayList<Double>(); // 存放检测数据数组
				// 保留两位小数的格式
				NumberFormat df = NumberFormat.getNumberInstance();
				df.setMaximumFractionDigits(2);
				// 读取文件内容
				File file = new File(filePath);
				BufferedReader reader = null;
				String temp = null;
				int line = 1;
				String[] strArray = null;
				// 左手12个通道；
				ArrayList<Double> z1 = new ArrayList<Double>();
				ArrayList<Double> z2 = new ArrayList<Double>();
				ArrayList<Double> z3 = new ArrayList<Double>();
				ArrayList<Double> z4 = new ArrayList<Double>();
				ArrayList<Double> z5 = new ArrayList<Double>();
				ArrayList<Double> z6 = new ArrayList<Double>();
				ArrayList<Double> z7 = new ArrayList<Double>();
				ArrayList<Double> z8 = new ArrayList<Double>();
				ArrayList<Double> z9 = new ArrayList<Double>();
				ArrayList<Double> z10 = new ArrayList<Double>();
				ArrayList<Double> z11 = new ArrayList<Double>();
				ArrayList<Double> z12 = new ArrayList<Double>();
				// 右手12个通道数组
				ArrayList<Double> y1 = new ArrayList<Double>();
				ArrayList<Double> y2 = new ArrayList<Double>();
				ArrayList<Double> y3 = new ArrayList<Double>();
				ArrayList<Double> y4 = new ArrayList<Double>();
				ArrayList<Double> y5 = new ArrayList<Double>();
				ArrayList<Double> y6 = new ArrayList<Double>();
				ArrayList<Double> y7 = new ArrayList<Double>();
				ArrayList<Double> y8 = new ArrayList<Double>();
				ArrayList<Double> y9 = new ArrayList<Double>();
				ArrayList<Double> y10 = new ArrayList<Double>();
				ArrayList<Double> y11 = new ArrayList<Double>();
				ArrayList<Double> y12 = new ArrayList<Double>();
				try {
					reader = new BufferedReader(new FileReader(file));
					while ((temp = reader.readLine()) != null && (temp = reader.readLine()).length() >= 85) {
						System.out.println("line" + line + "的长度:" + temp.length());
						// System.out.println("line"+line+":"+temp);
						line++;
						String temp1 = temp.trim();
						strArray = temp1.split(",");
						// String 转成double类型，并取绝对值
						Double l1 = Math.abs(Double.parseDouble(strArray[0]));
						Double l2 = Math.abs(Double.parseDouble(strArray[1]));
						Double l3 = Math.abs(Double.parseDouble(strArray[2]));
						Double l4 = Math.abs(Double.parseDouble(strArray[3]));
						Double l5 = Math.abs(Double.parseDouble(strArray[4]));
						Double l6 = Math.abs(Double.parseDouble(strArray[5]));
						Double l7 = Math.abs(Double.parseDouble(strArray[6]));
						Double l8 = Math.abs(Double.parseDouble(strArray[7]));
						Double l9 = Math.abs(Double.parseDouble(strArray[8]));
						Double l10 = Math.abs(Double.parseDouble(strArray[9]));
						Double l11 = Math.abs(Double.parseDouble(strArray[10]));
						Double l12 = Math.abs(Double.parseDouble(strArray[11]));
						Double r1 = Math.abs(Double.parseDouble(strArray[12]));
						Double r2 = Math.abs(Double.parseDouble(strArray[13]));
						Double r3 = Math.abs(Double.parseDouble(strArray[14]));
						Double r4 = Math.abs(Double.parseDouble(strArray[15]));
						Double r5 = Math.abs(Double.parseDouble(strArray[16]));
						Double r6 = Math.abs(Double.parseDouble(strArray[17]));
						Double r7 = Math.abs(Double.parseDouble(strArray[18]));
						Double r8 = Math.abs(Double.parseDouble(strArray[19]));
						Double r9 = Math.abs(Double.parseDouble(strArray[20]));
						Double r10 = Math.abs(Double.parseDouble(strArray[21]));
						Double r11 = Math.abs(Double.parseDouble(strArray[22]));
						Double r12 = Math.abs(Double.parseDouble(strArray[23]));
						// 将一列数据的值分别放入24个数组里面
						z1.add(l1);
						z2.add(l2);
						z3.add(l3);
						z4.add(l4);
						z5.add(l5);
						z6.add(l6);
						z7.add(l7);
						z8.add(l8);
						z9.add(l9);
						z10.add(l10);
						z11.add(l11);
						z12.add(l12);
						y1.add(r1);
						y2.add(r2);
						y3.add(r3);
						y4.add(r4);
						y5.add(r5);
						y6.add(r6);
						y7.add(r7);
						y8.add(r8);
						y9.add(r9);
						y10.add(r10);
						y11.add(r11);
						y12.add(r12);

					}
				}

				catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (reader != null) {
						try {
							reader.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					// 求每个数组的平均值
					int len = z1.size();
					double z1sum = 0;
					for (Double z : z1) {
						z1sum += z;
					}
					double z1ave = Double.valueOf(df.format(z1sum / len)); // 保留两位小数，并转换成double类型
					jc.add(z1ave); // 放到数组里；

					double z2sum = 0;
					for (Double z : z2) {
						z2sum += z;
					}
					double z2ave = Double.valueOf(df.format(z2sum / len)); // 保留两位小数，并转换成double类型
					jc.add(z2ave); // 放到数组里；

					double z3sum = 0;
					for (Double z : z3) {
						z3sum += z;
					}
					double z3ave = Double.valueOf(df.format(z3sum / len));
					jc.add(z3ave);

					double z4sum = 0;
					for (Double z : z4) {
						z4sum += z;
					}
					double z4ave = Double.valueOf(df.format(z4sum / len));
					jc.add(z4ave);

					double z5sum = 0;
					for (Double z : z5) {
						z5sum += z;
					}
					double z5ave = Double.valueOf(df.format(z5sum / len));
					jc.add(z5ave);

					double z6sum = 0;
					for (Double z : z6) {
						z6sum += z;
					}
					double z6ave = Double.valueOf(df.format(z6sum / len));
					jc.add(z6ave);

					double z7sum = 0;
					for (Double z : z7) {
						z7sum += z;
					}
					double z7ave = Double.valueOf(df.format(z7sum / len));
					jc.add(z7ave);

					double z8sum = 0;
					for (Double z : z8) {
						z8sum += z;
					}
					double z8ave = Double.valueOf(df.format(z8sum / len));
					jc.add(z8ave);

					double z9sum = 0;
					for (Double z : z9) {
						z9sum += z;
					}
					double z9ave = Double.valueOf(df.format(z9sum / len));
					jc.add(z9ave);

					double z10sum = 0;
					for (Double z : z10) {
						z10sum += z;
					}
					double z10ave = Double.valueOf(df.format(z10sum / len));
					jc.add(z10ave);

					double z11sum = 0;
					for (Double z : z11) {
						z11sum += z;
					}
					double z11ave = Double.valueOf(df.format(z11sum / len));
					jc.add(z11ave);

					double z12sum = 0;
					for (Double z : z12) {
						z12sum += z;
					}
					double z12ave = Double.valueOf(df.format(z12sum / len));
					jc.add(z12ave);

					double y1sum = 0;
					for (Double z : y1) {
						y1sum += z;
					}
					double y1ave = Double.valueOf(df.format(y1sum / len));
					jc.add(y1ave);

					double y2sum = 0;
					for (Double z : y2) {
						y2sum += z;
					}
					double y2ave = Double.valueOf(df.format(y2sum / len));
					jc.add(y2ave);

					double y3sum = 0;
					for (Double z : y3) {
						y3sum += z;
					}
					double y3ave = Double.valueOf(df.format(y3sum / len));
					jc.add(y3ave);

					double y4sum = 0;
					for (Double z : y4) {
						y4sum += z;
					}
					double y4ave = Double.valueOf(df.format(y4sum / len));
					jc.add(y4ave);

					double y5sum = 0;
					for (Double z : y5) {
						y5sum += z;
					}
					double y5ave = Double.valueOf(df.format(y5sum / len));
					jc.add(y5ave);

					double y6sum = 0;
					for (Double z : y6) {
						y6sum += z;
					}
					double y6ave = Double.valueOf(df.format(y6sum / len));
					jc.add(y6ave);

					double y7sum = 0;
					for (Double z : y7) {
						y7sum += z;
					}
					double y7ave = Double.valueOf(df.format(y7sum / len));
					jc.add(y7ave);

					double y8sum = 0;
					for (Double z : y8) {
						y8sum += z;
					}
					double y8ave = Double.valueOf(df.format(y8sum / len));
					jc.add(y8ave);

					double y9sum = 0;
					for (Double z : y9) {
						y9sum += z;
					}
					double y9ave = Double.valueOf(df.format(y9sum / len));
					jc.add(y9ave);

					double y10sum = 0;
					for (Double z : y10) {
						y10sum += z;
					}
					double y10ave = Double.valueOf(df.format(y10sum / len));
					jc.add(y10ave);

					double y11sum = 0;
					for (Double z : y11) {
						y11sum += z;
					}
					double y11ave = Double.valueOf(df.format(y11sum / len));
					jc.add(y11ave);

					double y12sum = 0;
					for (Double z : y12) {
						y12sum += z;
					}
					double y12ave = Double.valueOf(df.format(y12sum / len));
					jc.add(y12ave);

				}

				int n = 1;
				for (Double jcsj : jc) {
					System.out.println(n + ":" + jcsj);
					n++;
				}
				return jc;
	
	}
	//byte数组转16进制
	public static String byte2HexString(byte[] bytes) {
        String hex= "";
        if (bytes != null) {
            for (Byte b : bytes) {
                hex += String.format("%02X", b.intValue() & 0xFF);
            }
        }
        return hex;
    }
	
	//十六进制转Byte数组
	public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        try {
            for (int i = 0; i < len; i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i+1), 16));
            }
        } catch (Exception e) {
            //Log.d("", "Argument(s) for hexStringToByteArray(String s)"+ "was not a hex string");
        }
        return data;
    }
	
	
}
