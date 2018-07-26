package com.cdutcm.tcms.serialPort;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import gnu.io.UnsupportedCommOperationException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.TooManyListenersException;
import javax.print.attribute.standard.PrinterMessageFromOperator;

import com.cdutcm.core.util.DateUtil;

public class SendTest implements SerialPortEventListener {
	protected static CommPortIdentifier portid = null; // 通讯端口标识符
	protected static SerialPort comPort = null; // 串行端口
	protected int BAUD = 115200; // 波特率
	protected int DATABITS = SerialPort.DATABITS_8;; // 数据位
	protected int STOPBITS = SerialPort.STOPBITS_1; // 停止位
	protected int PARITY = SerialPort.PARITY_NONE; // 奇偶检验
	private static OutputStream outputStream; // 输出流
	private static InputStream inputStream; // 输入流
	private static byte[] readBuffer = new byte[1024]; // 4k的buffer空间,缓存串口读入的数据
	private String osName;
	StringBuilder buf = new StringBuilder(128);
	List<Double> db = new ArrayList<Double>();

	public SendTest() {

	}

	public static void main(String[] args) throws Exception {
		SendTest my = new SendTest();
		String com = my.listPortChoices();
		my.setSerialPortNumber(com);
		 String filePath = my.getFilePath();
		 my.getData(filePath);
		 sendMsg();
		// System.out.println(Double.MAX_VALUE);
	}

	// 读取所有串口
	public String listPortChoices() {
		CommPortIdentifier portId;
		Enumeration en = CommPortIdentifier.getPortIdentifiers();
		// iterate through the ports.
		int num = 0;
		while (en.hasMoreElements()) {
			portId = (CommPortIdentifier) en.nextElement();
			num++;
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
				System.out.println(num + ":  " + portId.getName());
				return portId.getName();
			}
		}
		return null;
	}

	// 设置串口号
	public void setSerialPortNumber(String com) {

		String osName = null;
		String osname = System.getProperty("os.name", "").toLowerCase();
		if (osname.startsWith("windows")) {
			// windows
			// osName = "COM3";
			osName = com;
		}
		System.out.println(osName);
		try {
			portid = CommPortIdentifier.getPortIdentifier(osName);
			// portid = CommPortIdentifier.getPortIdentifier(Port);
			if (portid.isCurrentlyOwned()) {
				System.out.println("端口在使用");
			} else {
				comPort = (SerialPort) portid.open(this.getClass().getName(), 1000);
			}
		} catch (PortInUseException e) {
			System.out.println("端口被占用");
			e.printStackTrace();

		} catch (NoSuchPortException e) {
			System.out.println("端口不存在");
			e.printStackTrace();
		}

		try {
			inputStream = comPort.getInputStream(); // 从COM1获取数据
			outputStream = comPort.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			comPort.addEventListener(this); // 给当前串口增加一个监听器
			comPort.notifyOnDataAvailable(true); // 当有数据是通知
		} catch (TooManyListenersException e) {
			e.printStackTrace();
		}
		try {
			// 设置串口参数依次为(波特率,数据位,停止位,奇偶检验)
			comPort.setSerialPortParams(this.BAUD, this.DATABITS, this.STOPBITS, this.PARITY);
		} catch (UnsupportedCommOperationException e) {
			System.out.println("端口操作命令不支持");
			e.printStackTrace();
		}

	}

	// 将输入的16进制string转成字节
	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	// 向串口发送信息方法
	public static void sendMsg() {
		String msg = "71340001";// 要发送的命令msg
		try {

			outputStream.write(hexStringToBytes(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 字节转换成十六进制字符串
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public void stop() {
		comPort.close();
	}

	public void getData(String filePath) throws Exception {
		List<Double> doubles = new ArrayList<Double>();
		List<String> strList = new ArrayList<String>();
		File file1 = new File(filePath);
		OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(file1), "UTF-8");
		BufferedWriter writer = new BufferedWriter(write);
		try {
			while (inputStream.available() >= 0) {
				String str = "" + inputStream.read();
				strList.add(str);
				// System.out.println(str);
				String list0 = strList.get(0);
				if (!"113".equals(list0) && !"114".equals(list0)) {
					strList = new ArrayList<String>();
				}
				if (strList.size() == 12 && strList.get(11).equals("255")) { // 12个是一条
					String sj = "";
					List<String> list = strList.subList(2, 9);
					for (String string : list) {
						string = "0" + string;
						sj += string;
					}
					if (sj.indexOf("-") == -1) {
						Double valueOf = Double.valueOf(sj);
						valueOf = valueOf / 1000;
						doubles.add(valueOf);
					}
					strList = new ArrayList<String>();
				}
				if (doubles.size() == 24) { // 24个是一组
					System.out.println(doubles);
					for (Double d : doubles) {
						BigDecimal bd = new BigDecimal(d).setScale(0, BigDecimal.ROUND_HALF_UP);
						long i = Long.parseLong(bd.toString());
						writer.write(i + ",");
					}
					writer.write("\r\n");
					writer.flush();
					doubles = new ArrayList<Double>();
				}
				Thread.sleep(20);
			}
		} catch (IOException e) {
			System.out.println("停止监测");
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		// getData();
		// System.out.println("data : "+data);
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
			break;
		}
	}

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
}