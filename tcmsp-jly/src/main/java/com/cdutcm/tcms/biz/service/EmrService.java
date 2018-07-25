package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;

public interface EmrService {
	int deleteByPrimaryKey(Long id);

	int insert(Emr record);

	int insertSelective(Emr record);

	Emr selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(Emr record);

	int updateByPrimaryKey(Emr record);

	List<Emr> listPagefindEmrByNo(Emr record);
	
	int deleteEmrAndRecipel(long id);
	
    List<Emr> findAllEmr();
}
