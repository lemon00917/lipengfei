package com.cdutcm.tcms.sys.service;

import java.util.List;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.sys.entity.Role;
import com.cdutcm.tcms.sys.entity.UserRole;

public interface UserRoleService {
	
	public int insertuserrole(UserRole userRole);
	
	public int  deleteuserrolebyuserid(long id);
	
	List<Role> selectrolebyuserid(long id);
	
    int deleteuserrolebyroleid(long id);
    
    List<Role> selectallrolebyuserid(long userid);
}
