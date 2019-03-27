package com.cdutcm.tcms.sys.mapper;

import com.cdutcm.tcms.sys.entity.RoleMenu;

public interface RoleMenuMapper {
         

    public int  insertrolemenu(RoleMenu roleMenu);
    
    public int  deleterolemenubyroleid(long id);
    
    int deleterolemenubymenuid(long id);

}
