package com.cdutcm.tcms.ajy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.tcms.ajy.model.FamilyMember;
import com.cdutcm.tcms.ajy.model.Jlzb;
import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;
import com.cdutcm.tcms.ajy.service.BaseJltzService;
import com.cdutcm.tcms.ajy.service.FamilyMemberService;
import com.cdutcm.tcms.ajy.service.JlzbService;
import com.cdutcm.tcms.ajy.service.RecordService;
import com.cdutcm.tcms.ajy.service.ReportService;

@RestController
@RequestMapping(value = "jlzb")
public class JlzbController {
	@Autowired
	private JlzbService jlzbService;
	@Autowired
	private FamilyMemberService fmservice;
	@Autowired
	private RecordService recordService;
	@Autowired
	private ReportService reportService;
/*	@RequestMapping("/jlzblist")
	public ModelAndView jlzblist(){
		ModelAndView mv=new ModelAndView("/jltz/base_jltz.html");
		Jlzb jlzb=jlzbService.rejlzb(); 
		mv.addObject("jlzb", jlzb);
		return mv;
	}*/
	
	/*经络指标数据*/
	@RequestMapping("/getChartData")
	public Jlzb getChartData(Long id){
		Record record=recordService.selectByUserId(id);//根据ID查询刚刚的检测报告		
		/* 获取检测结果数据*/
		 String result=record.getDetectionResult();
		 //根据检测数据，获取有问题的经络
		Jlzb jlzb=jlzbService.rejlzb(result);  	
		return jlzb;
	}
}
