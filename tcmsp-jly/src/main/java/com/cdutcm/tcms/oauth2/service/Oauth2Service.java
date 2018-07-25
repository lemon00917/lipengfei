package com.cdutcm.tcms.oauth2.service;


import com.cdutcm.tcms.oauth2.entity.Client;
import com.cdutcm.tcms.oauth2.entity.Oauth2code;
import com.cdutcm.tcms.oauth2.entity.Oauth2token;
import com.cdutcm.tcms.sys.entity.User;

public interface Oauth2Service {

	
	public Client getclient(Client client);
	
	public int insertclient(Client client);
	
	public int  deleteclientbyid(long id);
	
	public int updateclient(Client client);
	
	public int insertoauth2token(Oauth2token oauth2token);
	
	public int insertoauth2code(Oauth2code oauth2code);
	
	public User getuserbytoken(long acesstoken);
	
	public Oauth2code getoauth2code(String code);

}
