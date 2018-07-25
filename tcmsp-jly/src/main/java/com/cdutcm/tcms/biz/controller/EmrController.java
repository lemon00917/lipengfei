package com.cdutcm.tcms.biz.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.cdutcm.core.util.Const;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.model.RecipelItem;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.IllnessHistoryService;
import com.cdutcm.tcms.biz.service.PatientService;
import com.cdutcm.tcms.biz.service.RecipelItemService;
import com.cdutcm.tcms.biz.service.RecipelService;
import com.cdutcm.tcms.log.SysControllerLog;
import com.cdutcm.tcms.redis.service.IRedisService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 病历
 * 
 * @author fw
 * 
 */
@RestController
@RequestMapping(value = "emr")
public class EmrController {

	@Autowired
	private EmrService emrService;

	@Autowired
	private RecipelService recipelService;

	@Autowired
	private RecipelItemService recipelItemService;
	
	@Autowired
	private IRedisService redisService;
	@Autowired 
	private  PatientService patientService;
    @Autowired 
    private IllnessHistoryService illnessHistoryService;
	/**
	 * 发送
	 * 
	 * @param recipel
	 * @return
	 */
    @SysControllerLog(description="发送处方")
	@RequestMapping(value = "/addrecipel")
	public String sendRecipel(String recipel,HttpSession session) {
		System.out.println(recipel);
		Recipel r = new Recipel();
		Emr e = new Emr();
		try {
			r = new ObjectMapper().readValue(recipel, Recipel.class);
			// 默认都是新增
			IdWorker i = new IdWorker();
			List<RecipelItem> items = r.getRecipelItem();
			long recipelid = i.nextId();
			long emrid = i.nextId();
			long illnessHistoryId=i.nextId();
			r.setLastupdate(new Date());
			r.setId(recipelid);
			String emrVisitNo = (String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO);
			if(StringUtil.notEmpty(emrVisitNo)){
				e =redisService.get(emrVisitNo,Emr.class);
				if(e.getPatient()==null){
					return "发送失败,未选择病人!";	
				}
			}
			else{
				return "发送失败,没有诊断信息！";
			}
			// 如果有emrId则为新增
			if(r.getEmrId() != 0l){
				r.setEmrId(r.getEmrId());
			}else{
				// 插入病历
				e.setId(emrid);
				e.setCreateTime(new Date());
				if(e.getIllnessHistory()!=null){
					e.setIllnessHistoryId(illnessHistoryId);
					e.getIllnessHistory().setId(illnessHistoryId);
					illnessHistoryService.insert(e.getIllnessHistory());
				}
				emrService.insert(e);	
				r.setEmrId(emrid);
			}
			r.setStatus("已发送");
			recipelService.insert(r);
			for (RecipelItem item : items) {
				item.setId(i.nextId());
				item.setRecipelId(recipelid);
				item.setLastupdate(new Date());
				recipelItemService.insert(item);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return "发送失败！";
		}
		e.getPatient().setStatus("已诊");
		
		patientService.updateByPrimaryKeySelective(e.getPatient());
		session.removeAttribute(Const.SESSION_CURRENT_EMR_VISITNO);		
		session.removeAttribute(Const.SESSION_CURRENT_EMR);		
		System.out.println(r);
		return "发送成功--"+recipel;
	}
    @SysControllerLog(description="处方编辑")
	@RequestMapping(value = "/editrecipel")
    public String editRecipel(String recipel,Long id,HttpSession session){
		Recipel r = new Recipel();
		try {
			IdWorker i = new IdWorker();
			r = new ObjectMapper().readValue(recipel, Recipel.class);
			//编辑处方
			//Auto  病历修改
		    Emr emr=redisService.get((String)session.getAttribute(Const.SESSION_CURRENT_EMR_VISITNO),Emr.class);
		    emrService.updateByPrimaryKey(emr);
		    illnessHistoryService.updateByPrimaryKey(emr.getIllnessHistory());
			// 更新处方和处方详情
			r.setLastupdate(new Date());
			recipelService.updateByPrimaryKey(r);
			List<RecipelItem> items = r.getRecipelItem();
			// 删除处方详情表数据
			recipelItemService.deleteByPrimaryRecipelId(r.getId());
			for (RecipelItem item : items) {
				item.setId(i.nextId());
				item.setRecipelId(r.getId());
			    item.setLastupdate(new Date());
				recipelItemService.insert(item);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return "发送失败！";
		}
		session.removeAttribute(Const.SESSION_CURRENT_EMR_VISITNO);		
		session.removeAttribute(Const.SESSION_CURRENT_EMR);	
		
		return "编辑成功--"+recipel;
    }
	@RequestMapping(value = "/findRecipel")
	public ModelAndView findRecipel(Patient patient) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/kdys/patient_reicpels_info.html");
		mv.addObject("name", "松果医院非药物处方");
		return mv;

	}
	
}
