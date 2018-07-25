package com.cdutcm.tcms.oauth2.entity;

import java.util.Date;

public class Oauth2token {

   private long id;
   
   private String refresh_token;
   
   private String access_token;
   
   private String code;
   
   private String scope;
   
   private Date expires_in;

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public String getRefresh_token() {
	return refresh_token;
}

public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}

public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}

public String getCode() {
	return code;
}

public void setCode(String code) {
	this.code = code;
}

public String getScope() {
	return scope;
}

public void setScope(String scope) {
	this.scope = scope;
}

public Date getExpires_in() {
	return expires_in;
}

public void setExpires_in(Date expires_in) {
	this.expires_in = expires_in;
}
	
}
