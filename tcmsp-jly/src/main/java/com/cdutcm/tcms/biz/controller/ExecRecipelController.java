package com.cdutcm.tcms.biz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cdutcm.core.util.EmrToExecEmrUtil;
import com.cdutcm.core.util.StringUtil;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.ExecEmr;
import com.cdutcm.tcms.biz.model.Recipel;
import com.cdutcm.tcms.biz.service.EmrService;
import com.cdutcm.tcms.biz.service.ExecRecipelService;
import com.cdutcm.tcms.biz.service.RecipelService;

@RestController
@RequestMapping(value ="exec")
public class ExecRecipelController {
	
	@Autowired
	private ExecRecipelService execRecipelService;
	
	@Autowired
	private RecipelService recipelService;
	
	@Autowired
	private EmrService emrService;
	
	@RequestMapping(value = "/findemr")
	@ResponseBody
	public List<ExecEmr> findEmr(){
		List<Emr> emrs = emrService.findAllEmr();
		for (Emr emr : emrs){
			Recipel r = new Recipel();
			r.setEmrId(emr.getId());
			List<Recipel> recipel = recipelService.listPagefindRecipelById(r);
			if(!StringUtil.isEmptyList(recipel)){
				emr.setRecipels(recipel);
			}
		}	
		return EmrToExecEmrUtil.toExecEmr(emrs);
	}

}
