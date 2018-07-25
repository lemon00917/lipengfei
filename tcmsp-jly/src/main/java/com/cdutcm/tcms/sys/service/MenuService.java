package com.cdutcm.tcms.sys.service;

import java.util.List;

import com.cdutcm.tcms.sys.entity.Menu;

public interface MenuService {
	
	
	    int insertmenu(Menu menu);
	    
	    int updatemenu(Menu menu);
	    
	    int deletemenu(long menu_id);
	    
	    List<Menu> selectparentmenu();
	    
	    List<Menu> selectsubmenu();
	   
	    List<Menu> selectsubmenuelement();
	    
	    Menu selectmenubyid(long menu_id);
	    
	    List<Menu> selectmenubyroleid(long roleid,long parentid);
	    
	    List<Menu> selectmenubyuserid(long userid,long parentid);
}
