package com.cdutcm.tcms.sys.service;

import java.util.List;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.sys.entity.User;


public interface UserService {
    
	int insertuser(User user);
	
	List<User> listPageuser(User user);
	
    int	updateuser(User user);
    
    User selectuserbypsd(User user);
    
    User selectuserbyaccount(String account);
    
    int deleteuser(long id);
    
    User selectuserbyid(long id);
}
