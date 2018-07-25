package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.Patient;

public interface PatientMapper {
	int deleteByPrimaryKey(Long id);

	int insert(Patient record);

	int insertSelective(Patient record);

	Patient selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Patient record);

	int updateByPrimaryKey(Patient record);

	List<Patient> listPagePatients(Patient patient);

	List<Patient> findPatientsByNo(Patient patient);

}