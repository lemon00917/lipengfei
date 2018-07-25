package com.cdutcm.tcms.ajy.mapper;

import java.util.List;

import com.cdutcm.tcms.ajy.model.Dict;

public interface DictMapper {

	Dict selectByPrimaryKey(Long id);
	
	List<Dict> selectByPid(Long pid);
	
	List<Dict> listPageDict(Dict record);
	
	int deleteByPrimaryKey(Long id);
	
	int insert(Dict record);
	
	int updateByPrimaryKey(Dict record);
	
	int editname(Dict record);
	
	List<Dict> selectForCheck(Dict record);
	
	List<Dict> listallDict(Dict record);
}
