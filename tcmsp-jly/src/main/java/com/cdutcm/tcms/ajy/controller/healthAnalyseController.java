package com.cdutcm.tcms.ajy.controller;



//import static org.mockito.Mockito.ignoreStubs;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.DateUtil;
import com.cdutcm.tcms.ajy.DTO.ReportDTO;
import com.cdutcm.tcms.ajy.enums.ResultEnum;
import com.cdutcm.tcms.ajy.exception.JlyException;
import com.cdutcm.tcms.ajy.mapper.BaseJltzMapper;
import com.cdutcm.tcms.ajy.model.BaseJltz;
import com.cdutcm.tcms.ajy.model.FamilyMember;
import com.cdutcm.tcms.ajy.model.Record;
import com.cdutcm.tcms.ajy.model.Report;
import com.cdutcm.tcms.ajy.service.BaseJltzService;
import com.cdutcm.tcms.ajy.service.FamilyMemberService;
import com.cdutcm.tcms.ajy.service.RecordService;
import com.cdutcm.tcms.ajy.service.ReportDTOService;
import com.cdutcm.tcms.ajy.service.ReportService;
import com.cdutcm.tcms.sys.entity.User;


/**
 * @Auther: Mxq
 * @Date: 2018/4/26 
 * @Description: 健康分析
 */

@Controller
@RestController
public class healthAnalyseController {
	
	@Autowired
	private ReportDTOService reportDTOService;
	@Autowired
	private ReportService reportService;
	@Autowired
	private RecordService  recordService;
	@Autowired
	private FamilyMemberService aFamilyMemberService;
	
	@GetMapping("/healthAnalyse")
	public ModelAndView healthAnalyse(){
		
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/ajy/healthAnalyse.html");
        return mv;
	}
	//家主的健康分析
	@RequestMapping("/healthAnalyseContent")
	public ModelAndView healthAnalyseContent(HttpSession session){
		ModelAndView mv = new ModelAndView("/ajy/healthAnalyseContent.html");
		User user = (User)session.getAttribute(Const.SESSION_USER);
		Long userId=user.getId();  //获取userid	
		FamilyMember family=aFamilyMemberService.selectByUserId(userId); 
		Long id=family.getId(); //获取familymember主键ID
		//通过id查询所有记录
		List<ReportDTO>reportDTOs = reportDTOService.selcetByUserID(id);
		
		Report report=new Report();
		Record record=new Record();
		record.setFamilyMemberId(id);
		List<Report> reports=reportService.listPageReports(record);
		mv.addObject("family",family);
		mv.addObject("reports",reportDTOs);
		mv.addObject("length",reportDTOs.size());
		
		mv.addObject("reports1",reports);
		mv.addObject("report",report);
		mv.addObject("record",record);
        return mv;
	}
	
	//判断是否有健康分析数据
	@RequestMapping(value = "/healthinfo")
	public SysMsg healthinfo(Long id){
		SysMsg sm=new SysMsg();
		List<Record> records=recordService.selectRecords(id);		
		if(records.size()>0){
			sm.setStatus("TS");
		}else{
			sm.setStatus("FS");
		}
		return sm;
	}
	//家庭成员的健康分析
	@RequestMapping("/familyHealth")
	public ModelAndView familyHealth(Long id){
		ModelAndView mv = new ModelAndView("/ajy/healthAnalyseContent.html");
		FamilyMember family=aFamilyMemberService.selectByPrimaryKey(id);  //family是个人信息		
		//通过id查询所有记录
		Report report=new Report();
		List<ReportDTO>reportDTOs = reportDTOService.selcetByUserID(id);
		Record record=new Record();
		record.setFamilyMemberId(id);
		List<Report> reports=reportService.listPageReports(record);
		mv.addObject("family",family);
		mv.addObject("reports",reportDTOs);
		mv.addObject("length",reportDTOs.size());
		
		mv.addObject("reports1",reports);
		mv.addObject("report",report);
		mv.addObject("record",record);
        return mv;
	}
	
	//获取一个时间的检测报告
	@RequestMapping("/getjlbg")
	public ModelAndView getjlbg(Long familyMemberId,String gmtCreate){
		ModelAndView mv=new ModelAndView("/ajy/Analysejcbg.html");		
		Date gmtCreate1=DateUtil.parse(gmtCreate,DateUtil.YMDHMS);
		Record record=recordService.selectByDate(familyMemberId, gmtCreate1);
		if(record!=null){
			Long recordId= record.getId();
			ReportDTO reportDTO=reportDTOService.selcetByRecordId(recordId);
			mv.addObject("report",reportDTO);
		}
		return mv;
	}
}
