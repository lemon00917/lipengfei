package com.cdutcm.tcms.ajy.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.Const;
import com.cdutcm.tcms.ajy.model.BaseJltz;
import com.cdutcm.tcms.ajy.model.FamilyMember;
import com.cdutcm.tcms.ajy.model.Jlzb;
import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;
import com.cdutcm.tcms.ajy.service.BaseJltzService;
import com.cdutcm.tcms.ajy.service.FamilyMemberService;
import com.cdutcm.tcms.ajy.service.JlzbService;
import com.cdutcm.tcms.ajy.service.RecordService;
import com.cdutcm.tcms.ajy.service.ReportService;
import com.cdutcm.tcms.sys.entity.User;

@RestController
@RequestMapping(value = "jltz")
public class BaseJltzController {

	@Autowired
	private BaseJltzService baseJltzService;
	@Autowired
	private JlzbService jlzbService;
	@Autowired
	private FamilyMemberService fmservice;
	@Autowired
	private RecordService recordService;
	@Autowired
	private ReportService reportService;
	
	//打印报告
	@RequestMapping(value = "/jltzInfo")
	public ModelAndView selectJltz(String tzlx,HttpSession session){
		ModelAndView mv=new ModelAndView("/jltz/base_jltz.html");
		User user = (User)session.getAttribute(Const.SESSION_USER);
		Long userId=user.getId();  //获取userid	
		FamilyMember fm=fmservice.selectByPrimaryKey(userId);//获取检测人的信息	
		//根据userid获取检测结果
		List<Record> re=recordService.selectRecords(userId);
		
		tzlx="平和质";
		List<BaseJltz> tzs=baseJltzService.selectBytz(tzlx);
		mv.addObject("datas", tzs);
		mv.addObject("fmuser", fm);
		/*Jlzb jlzb=jlzbService.rejlzb(); 
		mv.addObject("jlzb", jlzb);*/
		return mv;			
	}
	
	@RequestMapping(value = "/familyjltz")
	public ModelAndView familyjltz(Long id){ //检测报告report的ID
		ModelAndView mv=new ModelAndView("/jltz/base_jltz.html");
//		根据reportid查询信息
		Report report= reportService.selectByPrimaryKey(id);
		//获取recordID
		Long recordid=report.getRecordId();
		//获取对应的recordid
		Record record=recordService.selectRecordtById(recordid);
		Long familyid=record.getFamilyMemberId(); //检测人的ID
		FamilyMember fm=fmservice.selectByPrimaryKey(familyid);//获取检测人的信息	
		/* 获取检测结果数据*/
		 String result=record.getDetectionResult();
		 Jlzb j= jlzbService.rejlzb(result); //根据检测数据，获取有问题的经络
		 String jl=j.getJl(); //经络
		 //根据经络名称 获取体质等各种信息
		 BaseJltz bz=baseJltzService.selectByjl(jl);
		 String tzbs=bz.getTzlx();//体质辨识
		 Long tzid=bz.getId(); //体质ID
		 String sj=bz.getSj();//获得四季养生
		 
		List<BaseJltz> tzs=baseJltzService.selectBytz(tzbs);
		mv.addObject("datas", tzs);
		mv.addObject("fmuser", fm);		 
		mv.addObject("jlzb", j);
		return mv;			
	}
	@RequestMapping("/mainJltz")
	public ModelAndView checkDetection(){
		return new ModelAndView("/ajy/checkDetection.html");
	}
	@RequestMapping(value = "/tojcbg")
	public ModelAndView tojcbg(Long id){//userid
		ModelAndView mv=new ModelAndView("/jltz/base_jltz.html");
		Record record=recordService.selectByUserId(id);//根据ID查询刚刚的检测报告
		FamilyMember fm=fmservice.selectByPrimaryKey(id);//获取检测人的信息	
		
		/* 获取检测结果数据*/
		 String result=record.getDetectionResult();
		//String result="19,24,26,23,15,32,23,23,17,18,18,19,19,17,10,16,14,13,15,14,28,26,24,29";
		 Jlzb j= jlzbService.rejlzb(result); //根据检测数据，获取有问题的经络
		 String jl=j.getJl(); //经络
		 //根据经络名称 获取体质等各种信息
		 BaseJltz bz=baseJltzService.selectByjl(jl);
		 String tzbs=bz.getTzlx();//体质辨识
		 Long tzid=bz.getId(); //体质ID
		 String sj=bz.getSj();//获得四季养生	 
		List<BaseJltz> tzs=baseJltzService.selectBytz(tzbs);
		mv.addObject("datas", tzs);
		mv.addObject("fmuser", fm);		 
		mv.addObject("jlzb", j);
		mv.addObject("result", result);
		mv.addObject("record", record);
		return mv;			
		
	}
}
