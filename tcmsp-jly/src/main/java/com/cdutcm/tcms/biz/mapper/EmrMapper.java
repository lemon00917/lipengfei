package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.Emr;

public interface EmrMapper {
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