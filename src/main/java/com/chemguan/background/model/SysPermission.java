package com.chemguan.background.model;

import java.util.List;

public class SysPermission {
	
	//菜单id
	private Integer sys_permission_id;
	
	//资源名称
	private String name;
	
	//资源类型:menu,button
	private String type;
	
	//url
	private String url;
	
	//权限代码
	private String percode;
	
	//父节点id
	private Integer parentid;
	
	//排序号
	private String sortstring;
	
	//是否可用 1：可用，0不可用
	private Integer available;
	
	//菜单等级
	private Integer level;
	
	private String subclass;
	
	//自定义子权限
	private List<SysPermission> listpermission;

	public Integer getSys_permission_id() {
		return sys_permission_id;
	}

	public void setSys_permission_id(Integer sysPermissionId) {
		sys_permission_id = sysPermissionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPercode() {
		return percode;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	public Integer getParentid() {
		return parentid;
	}

	public void setParentid(Integer parentid) {
		this.parentid = parentid;
	}

	public String getSortstring() {
		return sortstring;
	}

	public void setSortstring(String sortstring) {
		this.sortstring = sortstring;
	}

	public Integer getAvailable() {
		return available;
	}

	public void setAvailable(Integer available) {
		this.available = available;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public List<SysPermission> getListpermission() {
		return listpermission;
	}

	public void setListpermission(List<SysPermission> listpermission) {
		this.listpermission = listpermission;
	}

	public String getSubclass() {
		return subclass;
	}

	public void setSubclass(String subclass) {
		this.subclass = subclass;
	}
	
	
}
