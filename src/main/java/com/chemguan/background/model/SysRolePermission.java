package com.chemguan.background.model;

public class SysRolePermission {
	
	private Integer sys_role_permission_id;
	
	private Integer sys_role_id;
	
	private Integer sys_permission_id;
	
	private SysPermission syspermission;
	
	public Integer getSys_role_permission_id() {
		return sys_role_permission_id;
	}

	public Integer getSys_role_id() {
		return sys_role_id;
	}

	public void setSys_role_id(Integer sysRoleId) {
		sys_role_id = sysRoleId;
	}

	public Integer getSys_permission_id() {
		return sys_permission_id;
	}

	public void setSys_permission_id(Integer sysPermissionId) {
		sys_permission_id = sysPermissionId;
	}

	public void setSys_role_permission_id(Integer sysRolePermissionId) {
		sys_role_permission_id = sysRolePermissionId;
	}

	public SysPermission getSyspermission() {
		return syspermission;
	}

	public void setSyspermission(SysPermission syspermission) {
		this.syspermission = syspermission;
	}

	
	
	
	
}
