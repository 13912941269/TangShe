package com.chemguan.background.model;

import java.util.List;

public class ShiroUser {
	//账号
	private String username;
	
	//用户名
	private String realname;
	
	//角色名称
	private String rolename;
	
	//角色id
	private Integer roleid;
	
	//权限列表
	List<SysPermission> permissionlist;
	
	//权限列表菜单
	List<SysPermission> permissionmenulist;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public List<SysPermission> getPermissionlist() {
		return permissionlist;
	}

	public void setPermissionlist(List<SysPermission> permissionlist) {
		this.permissionlist = permissionlist;
	}

	public Integer getRoleid() {
		return roleid;
	}

	public void setRoleid(Integer roleid) {
		this.roleid = roleid;
	}

	public List<SysPermission> getPermissionmenulist() {
		return permissionmenulist;
	}

	public void setPermissionmenulist(List<SysPermission> permissionmenulist) {
		this.permissionmenulist = permissionmenulist;
	}
	
	
}
