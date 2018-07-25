package com.cdutcm.tcms.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.sys.entity.Role;
import com.cdutcm.tcms.sys.entity.UserRole;
import com.cdutcm.tcms.sys.mapper.UserRoleMapper;
import com.cdutcm.tcms.sys.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService{
	
	@Autowired
	private UserRoleMapper userMapper;

	@Override
	public int insertuserrole(UserRole userRole) {
		// TODO Auto-generated method stub
		return userMapper.insertuserrole(userRole);
	}

	@Override
	public int deleteuserrolebyuserid(long id) {
		// TODO Auto-generated method stub
		return userMapper.deleteuserrolebyuserid(id);
	}

	@Override
	public List<Role> selectrolebyuserid(long id) {
		// TODO Auto-generated method stub
		return userMapper.selectrolebyuserid(id);
	}

	@Override
	public int deleteuserrolebyroleid(long id) {
		// TODO Auto-generated method stub
		return userMapper.deleteuserrolebyroleid(id);
	}

	@Override
	public List<Role> selectallrolebyuserid(long userid) {
		// TODO Auto-generated method stub
		return userMapper.selectallrolebyuserid(userid);
	}

	
}
