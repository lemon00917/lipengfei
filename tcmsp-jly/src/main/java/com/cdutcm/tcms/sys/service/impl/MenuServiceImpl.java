package com.cdutcm.tcms.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdutcm.core.util.IdWorker;
import com.cdutcm.tcms.sys.entity.Menu;
import com.cdutcm.tcms.sys.mapper.MenuMapper;
import com.cdutcm.tcms.sys.service.MenuService;
@Service
public class MenuServiceImpl implements MenuService{

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public int insertmenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.insertmenu(menu);
	}

	@Override
	public int updatemenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuMapper.updatemenu(menu);
	}

	@Override
	public int deletemenu(long menu_id) {
		// TODO Auto-generated method stub
		return menuMapper.deletemenu(menu_id);
	}

	@Override
	public List<Menu> selectparentmenu() {
		// TODO Auto-generated method stub
		return menuMapper.selectparentmenu();
	}

	@Override
	public List<Menu> selectsubmenu() {
		// TODO Auto-generated method stub
		return menuMapper.selectsubmenu();
	}

	@Override
	public List<Menu> selectsubmenuelement() {
		// TODO Auto-generated method stub
		return menuMapper.selectsubmenuelement();
	}

	@Override
	public Menu selectmenubyid(long menu_id) {
		// TODO Auto-generated method stub
		return menuMapper.selectmenubyid(menu_id);
	}

	@Override
	public List<Menu> selectmenubyroleid(long roleid, long parentid) {
		// TODO Auto-generated method stub
		return menuMapper.selectmenubyroleid(roleid, parentid);
	}

	@Override
	public List<Menu> selectmenubyuserid(long userid, long parentid) {
		// TODO Auto-generated method stub
		return menuMapper.selectmenubyuserid(userid, parentid);
	}
	


}
