package com.cdutcm.tcms.ajy.service;

import java.util.List;

import com.cdutcm.tcms.ajy.model.BaseJltz;

public interface BaseJltzService {

	List<BaseJltz> selectBytz(String tzlx);
	
	BaseJltz selectById(Long id);
	
	BaseJltz selectByjl(String jl);
}
