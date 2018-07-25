package com.cdutcm.tcms.ajy.mapper;


import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdutcm.tcms.ajy.model.Record;


public interface RecordMapper {

	Record	selectByUserId(Long id);
	
	int insert(Record record);
	
	//通过id查询一条记录
	Record selectRecordById(Long id);
	//查找多条记录
	List<Record> selectRecords(Long id);
	
	Record selectByDate(@Param("familyMemberId")Long familyMemberId,@Param("gmtCreate")Date gmtCreate);
}
