package com.youxianji.pojo;

import java.util.Date;

public class AdminUser implements java.io.Serializable {

	
	private static final long serialVersionUID = -8726292618411453099L;

	/* 主键 */
	private String userId;
	
	/* 登录用户名 */
	private String userName;
	
	/* 用户姓名 */
	private String realName;
	
	/* 登录密码 */
	private String loginPass;
	
	/* 创建时间 */
	private Date createTime;
	
	/* 手机号 */
	private String telephone;
	
	/* 角色外键 */
	private AdminPersonRole role;
	
	/* 状态  0.正常 1.删除*/
	private String state;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getLoginPass() {
		return loginPass;
	}

	public void setLoginPass(String loginPass) {
		this.loginPass = loginPass;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public AdminPersonRole getRole() {
		return role;
	}

	public void setRole(AdminPersonRole role) {
		this.role = role;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	
	
}
