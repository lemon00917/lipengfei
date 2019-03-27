package com.cdutcm.tcms.ajy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.ajy.mapper.FamilyMemberMapper;
import com.cdutcm.tcms.ajy.model.FamilyMember;
import com.cdutcm.tcms.ajy.service.FamilyMemberService;

@Service
public class FamilyMemberServiceImpl implements FamilyMemberService{

	@Autowired
	private FamilyMemberMapper aFamilyMemberMapper;
	@Override
	public FamilyMember selectByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<FamilyMember> listPageAFamilyMember(FamilyMember record) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.listPageAFamilyMember(record);
	}

	@Override
	public int insert(FamilyMember record) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.insert(record);
	}

	@Override
	public int deleteByPrimaryKey(Long id) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKey(FamilyMember record) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.updateByPrimaryKey(record);
	}

	@Override
	public FamilyMember selectByUserId(Long uId) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.selectByUserId(uId);
	}

	@Override
	public List<FamilyMember> selectByRId(Long rid) {
		// TODO Auto-generated method stub
		return aFamilyMemberMapper.selectByRId(rid);
	}

}
