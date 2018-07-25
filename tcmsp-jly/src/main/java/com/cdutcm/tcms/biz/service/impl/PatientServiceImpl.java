package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.PatientMapper;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.service.PatientService;
import com.cdutcm.tcms.log.SysServiceLog;

@Service
public class PatientServiceImpl implements PatientService {
	@Autowired
	private PatientMapper patientMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Patient record) {
		// TODO Auto-generated method stub
		return patientMapper.insert(record);
	}

	@Override
	public int insertSelective(Patient record) {
		// TODO Auto-generated method stub
		return patientMapper.insertSelective(record);
	}

	@Override
	public Patient selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return patientMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Patient record) {
		// TODO Auto-generated method stub
		return patientMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Patient record) {
		// TODO Auto-generated method stub
		return patientMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Patient> listPagePatients(Patient patient) {
		return patientMapper.listPagePatients(patient);
	}

	@Override
	@SysServiceLog
	public List<Patient> findPatientsByNo(Patient patient) {
		// TODO Auto-generated method stub
		return patientMapper.findPatientsByNo(patient);
	}

}
