package com.cdutcm.ajy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.ajy.service.BaseJltzService;
import com.cdutcm.jly.entity.BaseJltz;
import com.cdutcm.jly.mapper.BaseJltzMapper;

@Service
public class BaseJltzServiceImpl implements BaseJltzService{

	@Autowired
	private BaseJltzMapper baseJltzMapper;

	@Override
	public List<BaseJltz> selectBytz(BaseJltz record) {
		return baseJltzMapper.selectBytz(record);
	}
}
