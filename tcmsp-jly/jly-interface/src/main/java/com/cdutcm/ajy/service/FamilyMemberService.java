package com.cdutcm.ajy.service;

import java.util.List;

import com.cdutcm.jly.entity.FamilyMember;

public interface FamilyMemberService {

	FamilyMember selectByPrimaryKey(Long id);
	
	List<FamilyMember> listPageAFamilyMember(FamilyMember record);
	
	FamilyMember selectByUserId(Long uId);
	
	int insert(FamilyMember record);
	
	int deleteByPrimaryKey(Long id);
	
	int updateByPrimaryKey(FamilyMember record);
	
	List<FamilyMember> selectByRId(Long rid);
}
