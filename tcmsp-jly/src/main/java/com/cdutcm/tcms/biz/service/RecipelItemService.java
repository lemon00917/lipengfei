package com.cdutcm.tcms.biz.service;

import java.util.List;

import com.cdutcm.tcms.biz.model.RecipelItem;

public interface RecipelItemService {
	int deleteByPrimaryEmrId(Long id);

	int insert(RecipelItem record);

	int insertSelective(RecipelItem record);

	RecipelItem selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(RecipelItem record);

	int updateByPrimaryKey(RecipelItem record);
	
    int deleteByPrimaryRecipelId(long id);
    
    List<String> findCtypeByRecipelId (RecipelItem record);
    
    List<RecipelItem> findRecipelItemByCtypeAndRecipelId(RecipelItem record);
}
