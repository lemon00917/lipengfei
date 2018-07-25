package com.cdutcm.tcms.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.sys.entity.User;
import com.cdutcm.tcms.sys.mapper.UserMapper;
import com.cdutcm.tcms.sys.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public int insertuser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> listPageuser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateuser(User user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectuserbypsd(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectuserbypsd(user);
	}

	@Override
	public User selectuserbyaccount(String account) {
		// TODO Auto-generated method stub
		return userMapper.selectuserbyaccount(account);
	}

	@Override
	public int deleteuser(long id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public User selectuserbyid(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
