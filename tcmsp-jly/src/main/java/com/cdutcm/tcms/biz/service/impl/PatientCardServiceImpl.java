package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.biz.mapper.PatientCardMapper;
import com.cdutcm.tcms.biz.model.PatientCard;
import com.cdutcm.tcms.biz.service.PatientCardService;

@Service
public class PatientCardServiceImpl implements PatientCardService{

	@Autowired 
	private PatientCardMapper patientCardMapper;
	@Override
	public SysMsg deleteByPrimaryKey(Long id) {
		SysMsg m = new SysMsg();
		int i=patientCardMapper.deleteByPrimaryKey(id);
		if (i > 0) {	
			m.setStatus("TS");
		} else {
			m.setStatus("FS");	
		}
		return m;
	}

	@Override
	public SysMsg insert(PatientCard record) {
		SysMsg msg = new SysMsg();
		IdWorker w = new IdWorker();
		long pcId=w.nextId();
		record.setId(pcId);
		int i=patientCardMapper.insert(record);
		if (i > 0) {	
			msg.setStatus("TS");
		} else {
			msg.setStatus("FS");	
		}
		return msg;
	}

	@Override
	public PatientCard selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientCardMapper.selectByPrimaryKey(id);
	}

	@Override
	public SysMsg updateByPrimaryKey(PatientCard record) {
		 SysMsg m=new SysMsg();
		 int i=patientCardMapper.updateByPrimaryKey(record);
		 if (i > 0) {	
				m.setStatus("TS");
			} else {
				m.setStatus("FS");	
			}
		return m;
	}

	@Override
	public List<PatientCard> listPagePatientCard(PatientCard record) {
		// TODO Auto-generated method stub
		return patientCardMapper.listPagePatientCard(record);
	}

	@Override
	public PatientCard selectBySelective(Long id) {
		// TODO Auto-generated method stub
		return patientCardMapper.selectBySelective(id);
	}

}
