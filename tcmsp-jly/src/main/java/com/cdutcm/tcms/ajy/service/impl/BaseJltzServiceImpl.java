package com.cdutcm.tcms.ajy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.ajy.mapper.BaseJltzMapper;
import com.cdutcm.tcms.ajy.model.BaseJltz;
import com.cdutcm.tcms.ajy.service.BaseJltzService;

@Service
public class BaseJltzServiceImpl implements BaseJltzService{

	@Autowired
	private BaseJltzMapper baseJltzMapper;

	@Override
	public List<BaseJltz> selectBytz(String tzlx) {
		// TODO Auto-generated method stub
		return baseJltzMapper.selectBytz(tzlx);
	}

	@Override
	public BaseJltz selectById(Long id) {
		// TODO Auto-generated method stub
		return baseJltzMapper.selectById(id);
	}

	@Override
	public BaseJltz selectByjl(String jl) {
		// TODO Auto-generated method stub
		return baseJltzMapper.selectByjl(jl);
	}
	
}
