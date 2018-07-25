package com.cdutcm.tcms.oauth2.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.tcms.oauth2.entity.Client;
import com.cdutcm.tcms.oauth2.entity.Oauth2code;
import com.cdutcm.tcms.oauth2.entity.Oauth2token;
import com.cdutcm.tcms.oauth2.mapper.Oauth2mapper;
import com.cdutcm.tcms.oauth2.service.Oauth2Service;
import com.cdutcm.tcms.sys.entity.User;

@Service
public class Oauth2ServiceImpl implements Oauth2Service {

	@Autowired
	private Oauth2mapper oauth2mapper;
	@Override
	public Client getclient(Client client) {
		// TODO Auto-generated method stub
		return oauth2mapper.getclient(client);
	}

	@Override
	public int insertclient(Client client) {
		// TODO Auto-generated method stub
		return oauth2mapper.insertclient(client);
	}

	@Override
	public int deleteclientbyid(long id) {
		// TODO Auto-generated method stub
		return oauth2mapper.deleteclientbyid(id);
	}

	@Override
	public int updateclient(Client client) {
		// TODO Auto-generated method stub
		return oauth2mapper.updateclient(client);
	}

	@Override
	public int insertoauth2token(Oauth2token oauth2token) {
		// TODO Auto-generated method stub
		return oauth2mapper.insertoauth2token(oauth2token);
	}

	@Override
	public int insertoauth2code(Oauth2code oauth2code) {
		// TODO Auto-generated method stub
		return oauth2mapper.insertoauth2code(oauth2code);
	}

	@Override
	public User getuserbytoken(long acesstoken) {
		// TODO Auto-generated method stub
		return oauth2mapper.getuserbytoken(acesstoken);
	}

	@Override
	public Oauth2code getoauth2code(String code) {
		// TODO Auto-generated method stub
		return oauth2mapper.getoauth2code(code);
	}

}
