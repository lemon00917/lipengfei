package com.cdutcm.tcms.biz.mapper;

import java.util.List;

import com.cdutcm.tcms.biz.model.RecipelItem;

public interface RecipelItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RecipelItem record);

    int insertSelective(RecipelItem record);

    RecipelItem selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RecipelItem record);

    int updateByPrimaryKey(RecipelItem record);
    
    int deleteByPrimaryRecipelId(long id);
    
    List<String> findCtypeByRecipelId (RecipelItem record);
    
    List<RecipelItem> findRecipelItemByCtypeAndRecipelId(RecipelItem record);
   
}