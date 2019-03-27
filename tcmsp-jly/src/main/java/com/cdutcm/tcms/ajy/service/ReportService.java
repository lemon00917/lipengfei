package com.cdutcm.tcms.ajy.service;

import java.util.List;

import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;

public interface ReportService {

	int insert(Report report);
	
	Report selectByReportId(Long id);
	
	Report selectByPrimaryKey(Long id);
	
	List<Report> listPageReports(Record record);
}
