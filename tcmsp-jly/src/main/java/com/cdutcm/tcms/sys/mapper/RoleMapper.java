package com.cdutcm.tcms.sys.mapper;

import java.util.List;

import com.cdutcm.tcms.sys.entity.Role;

public interface RoleMapper {
	
	int	insertrole(Role role);
	
	int deleterole(long id);
	
	int updaterole(Role role);
	
	List<Role> listPagerole(Role role);
	
    Role selectrolebyid(long id);
    List<Role> listAllRoles();
}
