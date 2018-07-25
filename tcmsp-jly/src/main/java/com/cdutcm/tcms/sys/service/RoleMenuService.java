package com.cdutcm.tcms.sys.service;

import com.cdutcm.core.message.SysMsg;
import com.cdutcm.tcms.sys.entity.RoleMenu;

public interface RoleMenuService {
      public SysMsg insertrolemenu(RoleMenu roleMenu);
      
      public SysMsg deleterolemenu(long id);
}
