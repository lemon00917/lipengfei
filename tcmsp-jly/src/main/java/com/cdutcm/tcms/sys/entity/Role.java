package com.cdutcm.tcms.sys.entity;

import com.cdutcm.core.page.Page;

public class Role {
	private long roleId;
	private String role_name;
    private String description;
    private Page page;
    private boolean hasrole;
	
	public boolean isHasrole() {
		return hasrole;
	}
	public void setHasrole(boolean hasrole) {
		this.hasrole = hasrole;
	}
	public Page getPage() {
		return page;
	}
	public void setPage(Page page) {
		this.page = page;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	
	
}

