package com.cdutcm.tcms.ajy.service;

import com.cdutcm.tcms.ajy.model.Report;

public interface ReportService {

	int insert(Report report);
	
	Report selectByReportId(Long id);
	
	Report selectByPrimaryKey(Long id);
}
