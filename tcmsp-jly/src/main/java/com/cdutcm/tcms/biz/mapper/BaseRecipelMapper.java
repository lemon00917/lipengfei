package com.cdutcm.tcms.biz.mapper;

import java.util.List;


import com.cdutcm.tcms.biz.model.BaseRecipel;

public interface BaseRecipelMapper {
	
	List<BaseRecipel> selectBySelective(BaseRecipel baserecipel);
	
	List<BaseRecipel> listPageBaseRecipel(BaseRecipel baserecipel);
	
	int deleteByPrimaryKey(Long id);

	int insert(BaseRecipel br);

	int insertSelective(BaseRecipel record);

	BaseRecipel selectByPrimaryKey(Long id);

	int updateByPrimaryKeySelective(BaseRecipel record);

	int updateByPrimaryKey(BaseRecipel record);

	List<BaseRecipel> listPagefindBaseRecipelByType(BaseRecipel record);

	BaseRecipel listPagefindBaseRecipelById(BaseRecipel record);

	List<BaseRecipel> findBaseRecipel(BaseRecipel record);

}