package com.chemguan.background.mapper;


import com.chemguan.background.model.*;
import java.util.List;
import java.util.Map;

@org.apache.ibatis.annotations.Mapper
public interface BackGroundLoginDao {
	/**
	 * 查询分页
	 */
	List<BackGroundLogin> selectAllBg();

	/**
	 * 删除
	 */
	int deleteBgById(Integer id);

	/**
	 * 注册
	 */
	int insertBg(BackGroundLogin bg);


	/**
	 * 查询用户名是否存在
	 */
	int checkBgname(String bgname);


	/**
	 * 查询出需要修改的数据
	 */
	BackGroundLogin selectBgById(Integer id);


	/**
	 * 修改
	 */
	int updateBg(Map map);


	/**
	 * 用户登陆
	 */
	BackGroundLogin loginback(Map map);


	/**
	 * 根据用户查询角色
	 */
	SysBgRole selectrolebybgid(Integer bgid);

	/**
	 * 根据角色查询权限
	 */
	List<SysRolePermission> selectpermissionbyroleid(Integer roleid);


	/**
	 * 查询所有角色
	 */
	List<SysRole> selectallrole();

	/**
	 * 检查角色名
	 */
	public Integer checkrolename(Map map);

	/**
	 * 添加角色
	 */
	public Integer insertrole(Map map);

	/**
	 * 根据id查询角色
	 */
	SysRole selectrolebyid(Integer roleid);

	/**
	 * 修改角色
	 */
	int updaterole(Map map);

	/**
	 * 赋予用户角色
	 */
	void insertsysbgrole(SysBgRole sbr);

	/**
	 * 删除用户角色
	 */
	void deletesysbgrole(Integer bgid);


	void deletesysbgrolebyroleid(Integer roleid);

	/**
	 * 查询所有权限
	 */
	List<SysPermission> selectallpermission(Map map);

	/**
	 * 删除角色所有权限
	 */
	void deletesyspermissionrole(Integer roleid);

	/**
	 * 添加角色权限
	 */
	void insertpermissionrole(List list);

	/**
	 * 查询所有可用权限数量
	 */
	Integer selectAllPermissionCount(Map map);

	/**
	 * 查询所有可用权限
	 */
	SysPermission selectAllPermission(Map map);

	void deleterole(Integer roleid);
}