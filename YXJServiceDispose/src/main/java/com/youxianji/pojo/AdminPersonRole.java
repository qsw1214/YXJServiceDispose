package com.youxianji.pojo;

import java.util.Date;

public class AdminPersonRole implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1525304553284008603L;

	/* 主键 */
	private String roleId;
	
	/* 角色名称 */
	private String roleName;
	
	/* 角色描述 */
	private String roleDesc;
	
	/* 创建时间 */
	private Date createTime;
	
	/* 状态  1.正常  0.删除 */
	private String state;
	


	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
}
