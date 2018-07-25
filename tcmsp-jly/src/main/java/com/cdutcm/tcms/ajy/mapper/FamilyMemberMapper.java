package com.cdutcm.tcms.ajy.mapper;

import java.util.List;

import com.cdutcm.tcms.ajy.model.FamilyMember;

public interface FamilyMemberMapper {

	FamilyMember selectByPrimaryKey(Long id);
	
	List<FamilyMember> selectByRId(Long rid);
	
	FamilyMember selectByUserId(Long uId);
	
	List<FamilyMember> listPageAFamilyMember(FamilyMember record);
	
	int insert(FamilyMember record);
	
	int deleteByPrimaryKey(Long id);
	
	int updateByPrimaryKey(FamilyMember record);
}
