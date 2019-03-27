package com.cdutcm.tcms.biz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.biz.mapper.EmrMapper;
import com.cdutcm.tcms.biz.model.Emr;
import com.cdutcm.tcms.biz.model.Patient;
import com.cdutcm.tcms.biz.service.EmrService;

@Service
public class EmrServiceImpl implements EmrService {
	@Autowired
	private EmrMapper emrMapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return emrMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.insert(record);
	}

	@Override
	public int insertSelective(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.insertSelective(record);
	}

	@Override
	public Emr selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return emrMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<Emr> listPagefindEmrByNo(Emr record) {
		// TODO Auto-generated method stub
		return emrMapper.listPagefindEmrByNo(record);
	}

	@Override
	public int deleteEmrAndRecipel(long id) {
		// TODO Auto-generated method stub
		return emrMapper.deleteEmrAndRecipel(id);
	}

	@Override
	public List<Emr> findAllEmr() {
		// TODO Auto-generated method stub
		return emrMapper.findAllEmr();
	}

}
