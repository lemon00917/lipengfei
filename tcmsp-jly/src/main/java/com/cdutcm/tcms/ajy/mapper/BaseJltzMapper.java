package com.cdutcm.tcms.ajy.mapper;

import java.util.List;

import com.cdutcm.tcms.ajy.model.BaseJltz;

public interface BaseJltzMapper {

	List<BaseJltz> selectBytz(String tzlx);
	
	BaseJltz selectById(Long id);
	//根据经络查询信息
	BaseJltz selectByjl(String jl);
}
