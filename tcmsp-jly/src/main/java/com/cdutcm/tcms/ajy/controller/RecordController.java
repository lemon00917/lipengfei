package com.cdutcm.tcms.ajy.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.IdWorker;
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

import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;

@RestController
@RequestMapping(value = "record")
public class RecordController {
	 @Autowired
	 private RecordService recordService;
	 @Autowired
	private FamilyMemberService aFamilyMemberService;
	 @Autowired
	 private ReportService reportService;
	 @Autowired
	 private JlzbService jlzbService;
	 
	 @Autowired
	 private BaseJltzService baseJltzService;
	 
	/*跳转到检测报告页面*/
	@RequestMapping(value="/recordMain")
	public ModelAndView toRecord(HttpSession session){
		ModelAndView mv=new ModelAndView("/jcbg/jcbg_main.html");
		User user= (User) session.getAttribute(Const.SESSION_USER);	
		Long userId=user.getId();  // 从seesion中得到user的信息并获取userid
		/*根据userid在family表中查询用户的信息*/
		FamilyMember familymember=aFamilyMemberService.selectByUserId(userId);
		Long rid=familymember.getId();//获取家人关联ID
		List<FamilyMember> family=aFamilyMemberService.selectByRId(rid); //获取关联家人信息
	    mv.addObject("user", user);
		mv.addObject("datas", family);
		return mv;
	}
	
	
	/*添加检测报告*/
	@RequestMapping(value="/addRecord",method = RequestMethod.POST)
	public SysMsg addRecord(Record record,HttpServletResponse response){
		 SysMsg sm=new SysMsg();
		 Report report=new Report();
		 IdWorker w = new IdWorker();
		 Long id=w.nextId();
		 record.setId(id); //ID
		 Date date=new Date();
		 record.setGmtCreate(date); //添加日期		 
			/* 获取检测结果数据*/
		 String result=record.getDetectionResult();
		 Jlzb j= jlzbService.rejlzb(result); //根据检测数据，获取有问题的经络
		 String jl=j.getJl(); //经络
		 //根据经络名称 获取体质等各种信息
		 BaseJltz bz=baseJltzService.selectByjl(jl);
		 String tzbs=bz.getTzlx();//体质辨识
		 Long tzid=bz.getId(); //体质ID
		 String sj=bz.getSj();//获得四季养生
		 Long sjid=null; //四季饮食ID
		 if(sj.equals("春季")){
			 sjid=(long) 1;
		 }
		 if(sj.equals("夏季")){
			 sjid=(long) 2;
		 }
		 if(sj.equals("秋季")){
			 sjid=(long) 3;
		 }
		 if(sj.equals("冬季")){
			 sjid=(long) 4;
		 }
		 /*同时向report表中添加检测结果数据*/
		 IdWorker iw = new IdWorker();
		 report.setRecordId(id);
		 report.setId(iw.nextId());
		 report.setHealthScore(Integer.toString(80)); //健康得分 默认80
		 report.setJltzId(tzid); //体质ID默认1
		 report.setSjysId(sjid); //四季饮食ID默认1
		 report.setTzbs(tzbs); //体质辨识默认肾虚
		 reportService.insert(report);
		int i=recordService.insert(record);
		if(i>0){
			sm.setStatus("TS");
			sm.setContent("提交成功！");
		}else{
			sm.setStatus("FS");
			sm.setContent("提交失败！");
		}
		return sm;	
	}
	
	@RequestMapping(value="/jcStart")
	public void  jcStart() throws Exception{
		recordService.jcStart();
	}
	
	@RequestMapping(value="/jcStop")
	public ArrayList<Double> closePort() throws IOException, NoSuchPortException, PortInUseException{	
		return recordService.closePort();
	}
}
