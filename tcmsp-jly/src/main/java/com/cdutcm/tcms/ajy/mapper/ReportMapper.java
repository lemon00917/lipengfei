package com.cdutcm.tcms.ajy.mapper;

import java.util.List;

import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;

public interface ReportMapper {

	int insert(Report report);
	
	Report selectReportById(Long id);
	
	Report selectByPrimaryKey(Long id);
	
	List<Report> listPageReports(Record record);
}
