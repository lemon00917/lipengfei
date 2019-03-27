package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.biz.model.BaseRecipelItem;

public interface BaseRecipelItemService {
	int deleteByPrimaryKey(Long id);

	int insert(BaseRecipelItem item);

	int insertSelective(BaseRecipelItem record);

	BaseRecipelItem selectByPrimaryKey(Long id);

	List<BaseRecipelItem>  listBRItem(Long id);
	 
	int updateByPrimaryKeySelective(BaseRecipelItem record);

	int updateByPrimaryKey(BaseRecipelItem record);
	
	int replaceByPrimaryKey(BaseRecipelItem record);

}
