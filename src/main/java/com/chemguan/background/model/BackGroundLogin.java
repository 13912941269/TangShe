package com.chemguan.background.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Data
@Table(name = "backgroundlogin")
@NoArgsConstructor
@AllArgsConstructor
public class BackGroundLogin{
	@Id
	private Integer id;
	// 账号
	private String username;
	// 密码
	private String password;
	//用户名
	private String realname;
	//自定义角色名
	@Transient
	private String rolename;
	//角色id
	@Transient
	private Integer roleid;
}
