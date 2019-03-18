package com.chemguan.background.model;

public class SysBgRole {

	private Integer sys_bg_role_id;
	
	private Integer sys_bg_id;
	
	private Integer sys_role_id;
	
	//自定义角色名称
	private String name;

	public Integer getSys_bg_role_id() {
		return sys_bg_role_id;
	}

	public void setSys_bg_role_id(Integer sysBgRoleId) {
		sys_bg_role_id = sysBgRoleId;
	}

	public Integer getSys_bg_id() {
		return sys_bg_id;
	}

	public void setSys_bg_id(Integer sysBgId) {
		sys_bg_id = sysBgId;
	}

	public Integer getSys_role_id() {
		return sys_role_id;
	}

	public void setSys_role_id(Integer sysRoleId) {
		sys_role_id = sysRoleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
