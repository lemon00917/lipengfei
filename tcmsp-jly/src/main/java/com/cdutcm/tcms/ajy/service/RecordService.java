package com.cdutcm.tcms.ajy.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.service.impl.RecordServiceImpl;

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

public interface RecordService {
	int insert(Record record);

	Record selectRecordtById(Long id);

	// 查询多条记录
	List<Record> selectRecords(Long fimily_member_id);

	// 根据日期查询
	Record selectByDate(Long id, Date gmtCreate);

	void jcStart() throws Exception;

	ArrayList<Double> closePort() throws IOException, NoSuchPortException, PortInUseException;

	ArrayList<Double> jcsj();

	Record	selectByUserId(Long id);
}
