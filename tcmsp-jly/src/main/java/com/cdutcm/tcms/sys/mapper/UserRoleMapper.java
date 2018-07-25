package com.cdutcm.tcms.sys.mapper;

import java.util.List;

import com.cdutcm.tcms.sys.entity.Role;
import com.cdutcm.tcms.sys.entity.UserRole;

public interface UserRoleMapper {

	public int insertuserrole(UserRole userRole);
	
	public int  deleteuserrolebyuserid(long id);
	
	List<Role> selectrolebyuserid(long id);
	
    int deleteuserrolebyroleid(long id);
    
    List<Role> selectallrolebyuserid(long userid);
}

