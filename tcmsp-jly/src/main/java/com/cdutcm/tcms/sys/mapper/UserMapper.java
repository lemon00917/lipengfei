package com.cdutcm.tcms.sys.mapper;

import java.util.List;

import com.cdutcm.tcms.sys.entity.User;


public interface UserMapper  {
	
	int insertuser(User user);
	
	List<User> listPageuser(User user);
	
    int	updateuser(User user);
    
    User selectuserbypsd(User user);
    
    User selectuserbyaccount(String account);
    
    int deleteuser(long id);
    
    User selectuserbyid(long id);
}
