package com.cdutcm.tcms.sys.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.cdutcm.tcms.sys.entity.Menu;

public interface MenuMapper {
	
    int insertmenu(Menu menu);
   
    int updatemenu(Menu menu);
    
    int deletemenu(long menu_id);
    
    List<Menu> selectparentmenu();
    
    List<Menu> selectsubmenu();
   
    List<Menu> selectsubmenuelement();
    
    Menu selectmenubyid(long menu_id);
    
    List<Menu> selectmenubyroleid(@Param("roleid") long roleid,@Param("parentid")long parentid);
    
    List<Menu> selectmenubyuserid(@Param("userid") long userid,@Param("parentid")long parentid);
}
