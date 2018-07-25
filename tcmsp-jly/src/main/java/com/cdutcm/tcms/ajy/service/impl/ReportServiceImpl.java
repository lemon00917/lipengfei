package com.cdutcm.tcms.ajy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.ajy.mapper.ReportMapper;
import com.cdutcm.tcms.ajy.model.Report;
import com.cdutcm.tcms.ajy.service.ReportService;

@Service
public class ReportServiceImpl implements ReportService{

	@Autowired
	private ReportMapper reportMapper;

	@Override
	public int insert(Report report) {
		// TODO Auto-generated method stub
		return reportMapper.insert(report);
	}

	@Override
	public Report selectByReportId(Long id) {
		// TODO Auto-generated method stub
		return reportMapper.selectReportById(id);
	}

	@Override
	public Report selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return reportMapper.selectByPrimaryKey(id);
	}

	
	
	
}
