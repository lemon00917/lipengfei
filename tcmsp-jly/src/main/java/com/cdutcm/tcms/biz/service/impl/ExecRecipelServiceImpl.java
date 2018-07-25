package com.cdutcm.tcms.biz.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cdutcm.tcms.biz.mapper.ExecRecipelMapper;
import com.cdutcm.tcms.biz.model.ExecRecipel;
import com.cdutcm.tcms.biz.service.ExecRecipelService;

@Service
public class ExecRecipelServiceImpl implements ExecRecipelService {
   @Autowired
   private ExecRecipelMapper  execRecipelMapper;
	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return execRecipelMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(ExecRecipel record) {
		// TODO Auto-generated method stub
		return execRecipelMapper.insert(record);
	}

	@Override
	public int insertSelective(ExecRecipel record) {
		// TODO Auto-generated method stub
		return execRecipelMapper.insertSelective(record);
	}

	@Override
	public ExecRecipel selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return execRecipelMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(ExecRecipel record) {
		// TODO Auto-generated method stub
		return execRecipelMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(ExecRecipel record) {
		// TODO Auto-generated method stub
		return execRecipelMapper.updateByPrimaryKey(record);
	}

}
