package com.cdutcm.tcms.ajy.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.util.DateUtil;
import com.cdutcm.tcms.ajy.mapper.RecordMapper;
import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.service.RecordService;
import com.cdutcm.tcms.serialPort.SendTest;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

@Service
public class RecordServiceImpl implements RecordService, SerialPortEventListener {

	@Autowired
	private RecordMapper recordMapper;

	SendTest my = new SendTest();

	private InputStream inputStream = null; // 端口输入流
	private CommPortIdentifier com4 = null;// 未打卡的端口

	private String filePath = null;

	// 获取filepath
	public String getFilePath() {
		String fileName = DateUtil.format(new Date(), "yyyyMMddHHmmss") + ".txt";// 新的文件名
		String path = "e:/jly";
		String filePath = path + "/" + fileName; // 文件路径
		return filePath;
	}

	// 获取Writer
	public BufferedWriter getWriter() throws NoSuchPortException, PortInUseException, IOException {
		String filePath = getFilePath(); // 文件路径
		File file1 = new File(filePath);
		if (!file1.exists() && !file1.isDirectory()) {
			file1.createNewFile();
		}
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file1), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		return writer;

	}

	@Override
	public int insert(Record record) {
		// TODO Auto-generated method stub
		return recordMapper.insert(record);
	}

	@Override
	public Record selectRecordtById(Long id) {
		// TODO Auto-generated method stub
		return recordMapper.selectRecordById(id);
	}

	@Override
	public List<Record> selectRecords(Long fimily_member_id) {
		// TODO Auto-generated method stub
		List<Record> records = recordMapper.selectRecords(fimily_member_id);
		return records;
	}

	@Override
	public Record selectByDate(Long id, Date gmtCreate) {
		// TODO Auto-generated method stub
		return recordMapper.selectByDate(id, gmtCreate);
	}

	@Override
	public void jcStart() throws Exception {
		filePath = getFilePath();
		String comPort = my.listPortChoices();
		my.setSerialPortNumber(comPort);
		my.getData(filePath);
	}

	public List<Integer> getData(SerialPortEvent event) {
		List<Integer> doubles = new ArrayList<Integer>(); // 返回数据
		List<String> strList = new ArrayList<String>(); // 存每一条数据
		switch (event.getEventType()) {
		case SerialPortEvent.BI:// Break interrupt,通讯中断
		case SerialPortEvent.OE:// Overrun error，溢位错误
		case SerialPortEvent.FE:// Framing error，传帧错误
		case SerialPortEvent.PE:// Parity error，校验错误
		case SerialPortEvent.CD:// Carrier detect，载波检测
		case SerialPortEvent.CTS:// Clear to send，清除发送
		case SerialPortEvent.DSR:// Data set ready，数据设备就绪
		case SerialPortEvent.RI:// Ring indicator，响铃指示
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据,并且给串口返回数据
			int availableBytes = 0;
			try {
				availableBytes = inputStream.available();
				while (inputStream.available() > 0) {
					String str = "" + inputStream.read();
					strList.add(str);
					if (strList.size() == 12) { // 12个是一条
						String sj = "";
						List<String> list = strList.subList(2, 9);
						for (String string : list) {							
							sj += string;
						}
						int a = Integer.parseInt(sj);
						System.out.println("a:"+a);					
						Double b=5*41.2*a;
						System.out.println("b:"+b);
						Double c=(double) (4*4096-5*a);
						System.out.println("c:"+c);
						Integer d= (int) (b/c);						
					
						if(d>=20&&d<=910){
							System.out.println("规范的d:"+d);
							doubles.add( d);
						}else{
							System.out.println("不规范的值："+d);
						}
						strList = new ArrayList<String>();
					}
					if (doubles.size() == 24) { // 24个是一组
						System.out.println(doubles);
						doubles = new ArrayList<Integer>();
					}
				}
				availableBytes = inputStream.available();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
		return doubles;
	}

	// 将获取的数据处理 获得平均值，再根据算法，得出体质等信息
	@Override
	public ArrayList<Double> closePort() throws IOException, NoSuchPortException, PortInUseException {
		my.stop();
//		BufferedWriter writer = getWriter();
//		writer.flush();
//		writer.close();
		// 读取文件
		// 读取文件
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
			Thread.sleep(100);//保证先停止再读取
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
			System.out.println("没数据了");
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
			double z1ave =  z1sum /  len; // 保留两位小数，并转换成double类型

			jc.add(z1ave); // 放到数组里；

			double z2sum = 0;
			for (Double z : z2) {
				z2sum += z;
			}
			double z2ave =  z2sum /  len; // 保留两位小数，并转换成double类型
			jc.add(z2ave); // 放到数组里；

			double z3sum = 0;
			for (Double z : z3) {
				z3sum += z;
			}
			double z3ave =  z3sum /  len;
			jc.add(z3ave);

			double z4sum = 0;
			for (Double z : z4) {
				z4sum += z;
			}
			double z4ave =  z4sum /  len;
			jc.add(z4ave);

			double z5sum = 0;
			for (Double z : z5) {
				z5sum += z;
			}
			double z5ave =  z5sum /  len;
			jc.add(z5ave);

			double z6sum = 0;
			for (Double z : z6) {
				z6sum += z;
			}
			double z6ave =  z6sum /  len;
			jc.add(z6ave);

			double z7sum = 0;
			for (Double z : z7) {
				z7sum += z;
			}
			double z7ave =  z7sum /  len;
			jc.add(z7ave);

			double z8sum = 0;
			for (Double z : z8) {
				z8sum += z;
			}
			double z8ave =  z8sum /  len;
			jc.add(z8ave);

			double z9sum = 0;
			for (Double z : z9) {
				z9sum += z;
			}
			double z9ave =  z9sum /  len;
			jc.add(z9ave);

			double z10sum = 0;
			for (Double z : z10) {
				z10sum += z;
			}
			double z10ave =  z10sum /  len;
			jc.add(z10ave);

			double z11sum = 0;
			for (Double z : z11) {
				z11sum += z;
			}
			double z11ave =  z11sum /  len;
			jc.add(z11ave);

			double z12sum = 0;
			for (Double z : z12) {
				z12sum += z;
			}
			double z12ave =  z12sum /  len;
			jc.add(z12ave);

			double y1sum = 0;
			for (Double z : y1) {
				y1sum += z;
			}
			double y1ave =  y1sum /  len;
			jc.add(y1ave);

			double y2sum = 0;
			for (Double z : y2) {
				y2sum += z;
			}
			double y2ave =  y2sum /  len;
			jc.add(y2ave);

			double y3sum = 0;
			for (Double z : y3) {
				y3sum += z;
			}
			double y3ave =  y3sum /  len;
			jc.add(y3ave);

			double y4sum = 0;
			for (Double z : y4) {
				y4sum += z;
			}
			double y4ave =  y4sum /  len;
			jc.add(y4ave);

			double y5sum = 0;
			for (Double z : y5) {
				y5sum += z;
			}
			double y5ave =  y5sum /  len;
			jc.add(y5ave);

			double y6sum = 0;
			for (Double z : y6) {
				y6sum += z;
			}
			double y6ave =  y6sum /  len;
			jc.add(y6ave);

			double y7sum = 0;
			for (Double z : y7) {
				y7sum += z;
			}
			double y7ave =  y7sum /  len;
			jc.add(y7ave);

			double y8sum = 0;
			for (Double z : y8) {
				y8sum += z;
			}
			double y8ave =  y8sum /  len;
			jc.add(y8ave);

			double y9sum = 0;
			for (Double z : y9) {
				y9sum += z;
			}
			double y9ave =  y9sum /  len;
			jc.add(y9ave);

			double y10sum = 0;
			for (Double z : y10) {
				y10sum += z;
			}
			double y10ave =  y10sum /  len;
			jc.add(y10ave);

			double y11sum = 0;
			for (Double z : y11) {
				y11sum += z;
			}
			double y11ave =  y11sum /  len;
			jc.add(y11ave);

			double y12sum = 0;
			for (Double z : y12) {
				y12sum += z;
			}
			double y12ave =  y12sum /  len;
			jc.add(y12ave);

		}

//		int n = 1;
//		for (Double jcsj : jc) {
//			System.out.println(n + ":" + jcsj);
//			n++;
//		}
		return jc;

	}

	@Override
	public ArrayList<Double> jcsj() {
		// 读取文件
		ArrayList<Double> jc = new ArrayList<Double>(); // 存放检测数据数组
		// 保留两位小数的格式
		NumberFormat df = NumberFormat.getNumberInstance();
		df.setMaximumFractionDigits(2);
		// 读取文件内容
		File file = new File("");
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
			double z1ave =  z1sum /  len; // 保留两位小数，并转换成double类型
			jc.add(z1ave); // 放到数组里；

			double z2sum = 0;
			for (Double z : z2) {
				z2sum += z;
			}
			double z2ave =  z2sum /  len; // 保留两位小数，并转换成double类型
			jc.add(z2ave); // 放到数组里；

			double z3sum = 0;
			for (Double z : z3) {
				z3sum += z;
			}
			double z3ave =  z3sum /  len;
			jc.add(z3ave);

			double z4sum = 0;
			for (Double z : z4) {
				z4sum += z;
			}
			double z4ave =  z4sum /  len;
			jc.add(z4ave);

			double z5sum = 0;
			for (Double z : z5) {
				z5sum += z;
			}
			double z5ave =  z5sum /  len;
			jc.add(z5ave);

			double z6sum = 0;
			for (Double z : z6) {
				z6sum += z;
			}
			double z6ave =  z6sum /  len;
			jc.add(z6ave);

			double z7sum = 0;
			for (Double z : z7) {
				z7sum += z;
			}
			double z7ave =  z7sum /  len;
			jc.add(z7ave);

			double z8sum = 0;
			for (Double z : z8) {
				z8sum += z;
			}
			double z8ave =  z8sum /  len;
			jc.add(z8ave);

			double z9sum = 0;
			for (Double z : z9) {
				z9sum += z;
			}
			double z9ave =  z9sum /  len;
			jc.add(z9ave);

			double z10sum = 0;
			for (Double z : z10) {
				z10sum += z;
			}
			double z10ave =  z10sum /  len;
			jc.add(z10ave);

			double z11sum = 0;
			for (Double z : z11) {
				z11sum += z;
			}
			double z11ave =  z11sum /  len;
			jc.add(z11ave);

			double z12sum = 0;
			for (Double z : z12) {
				z12sum += z;
			}
			double z12ave =  z12sum /  len;
			jc.add(z12ave);

			double y1sum = 0;
			for (Double z : y1) {
				y1sum += z;
			}
			double y1ave =  y1sum /  len;
			jc.add(y1ave);

			double y2sum = 0;
			for (Double z : y2) {
				y2sum += z;
			}
			double y2ave =  y2sum /  len;
			jc.add(y2ave);

			double y3sum = 0;
			for (Double z : y3) {
				y3sum += z;
			}
			double y3ave =  y3sum /  len;
			jc.add(y3ave);

			double y4sum = 0;
			for (Double z : y4) {
				y4sum += z;
			}
			double y4ave =  y4sum /  len;
			jc.add(y4ave);

			double y5sum = 0;
			for (Double z : y5) {
				y5sum += z;
			}
			double y5ave =  y5sum /  len;
			jc.add(y5ave);

			double y6sum = 0;
			for (Double z : y6) {
				y6sum += z;
			}
			double y6ave =  y6sum /  len;
			jc.add(y6ave);

			double y7sum = 0;
			for (Double z : y7) {
				y7sum += z;
			}
			double y7ave =  y7sum /  len;
			jc.add(y7ave);

			double y8sum = 0;
			for (Double z : y8) {
				y8sum += z;
			}
			double y8ave =  y8sum /  len;
			jc.add(y8ave);

			double y9sum = 0;
			for (Double z : y9) {
				y9sum += z;
			}
			double y9ave =  y9sum /  len;
			jc.add(y9ave);

			double y10sum = 0;
			for (Double z : y10) {
				y10sum += z;
			}
			double y10ave =  y10sum /  len;
			jc.add(y10ave);

			double y11sum = 0;
			for (Double z : y11) {
				y11sum += z;
			}
			double y11ave =  y11sum /  len;
			jc.add(y11ave);

			double y12sum = 0;
			for (Double z : y12) {
				y12sum += z;
			}
			double y12ave =  y12sum /  len;
			jc.add(y12ave);

		}

		int n = 1;
		for (Double jcsj : jc) {
			System.out.println(n + ":" + jcsj);
			n++;
		}
		return jc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see gnu.io.SerialPortEventListener#serialEvent(gnu.io.SerialPortEvent)
	 */
	@Override
	public void serialEvent(SerialPortEvent event) {
		switch (event.getEventType()) {
		case SerialPortEvent.BI:// Break interrupt,通讯中断
		case SerialPortEvent.OE:// Overrun error，溢位错误
		case SerialPortEvent.FE:// Framing error，传帧错误
		case SerialPortEvent.PE:// Parity error，校验错误
		case SerialPortEvent.CD:// Carrier detect，载波检测
		case SerialPortEvent.CTS:// Clear to send，清除发送
		case SerialPortEvent.DSR:// Data set ready，数据设备就绪
		case SerialPortEvent.RI:// Ring indicator，响铃指示
		case SerialPortEvent.OUTPUT_BUFFER_EMPTY:
			break;
		case SerialPortEvent.DATA_AVAILABLE:// 当有可用数据时读取数据,并且给串口返回数据
			try {
				List<Double> doubles = new ArrayList<Double>(); // 返回数据
				List<String> strList = new ArrayList<String>(); // 存每一条数据
				// 多次读取,将所有数据读入
				// List<String> result = new ArrayList<String>();
				while (inputStream.available() > 0) {
					String str = "" + inputStream.read();
					strList.add(str);
					if (strList.size() == 12) { // 12个是一条
						String sj = "";
						List<String> list = strList.subList(2, 9);
						for (String string : list) {
							
							sj += string;
						}
						int a = Integer.parseInt(sj);
						System.out.println("a:"+a);					
						Double b=5*41.2*a;
						System.out.println("b:"+b);
						Double c=(double) (4*4096-5*a);
						System.out.println("c:"+c);
						Double d= (b/c);						
						System.out.println("d:"+d);
						
						doubles.add( d);
						strList = new ArrayList<String>();
						
						/*Double valueOf = Double.valueOf(sj);
						valueOf = valueOf / 1000;
						doubles.add(valueOf);
						strList = new ArrayList<String>();*/
					}
					if (doubles.size() == 24) { // 24个是一组
						System.out.println(doubles);
						doubles = new ArrayList<Double>();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	@Override
	public Record selectByUserId(Long id) {
		// TODO Auto-generated method stub
		return recordMapper.selectByUserId(id);
	}

}
