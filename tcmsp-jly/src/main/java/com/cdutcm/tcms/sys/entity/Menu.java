package com.cdutcm.tcms.sys.entity;

import java.util.List;

import com.cdutcm.core.page.Page;

/**
 * @author       zhufj
 * @Description  菜单
 * @Date         2016-9-20
 */
public class Menu {
	
	private long id;
	
	private long menu_id;
	
	private long parent_id;
	
	private String menu_name;
	
	private String menu_type;
	
	private String params;
	
	private String icon;
	
	private boolean hasmenu;
	
	private String menu_url;
	
	public String getMenu_url() {
		return menu_url;
	}

	public void setMenu_url(String menu_url) {
		this.menu_url = menu_url;
	}

	private List<Menu> submenu;
	
	public List<Menu> getSubmenu() {
		return submenu;
	}

	public void setSubmenu(List<Menu> submenu) {
		this.submenu = submenu;
	}

	private Page page;

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(long menu_id) {
		this.menu_id = menu_id;
	}

	

	public long getParent_id() {
		return parent_id;
	}

	public void setParent_id(long parent_id) {
		this.parent_id = parent_id;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}

	public String getParams() {
		return params;
	}

	public void setParams(String params) {
		this.params = params;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public boolean isHasmenu() {
		return hasmenu;
	}

	public void setHasmenu(boolean hasmenu) {
		this.hasmenu = hasmenu;
	}


	
	
}
