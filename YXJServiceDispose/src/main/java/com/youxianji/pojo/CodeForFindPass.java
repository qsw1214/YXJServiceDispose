package com.youxianji.pojo;

import java.util.Date;

/**
 * Codeforfindpass entity. @author MyEclipse Persistence Tools
 */

public class CodeForFindPass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4921038633077860763L;

	// Fields
	/* 主键 */
	private String id;
	
	/* 手机号 */
	private String telephone;
	
	/* 生成时间 */
	private Date createTime;
	
	/* 验证码 */
	private String verifyCode;
	
	/* 状态 */
	private String state;//1.有效 2.失效

	// Constructors

	/** default constructor */
	public CodeForFindPass() {
	}

	/** full constructor */
	public CodeForFindPass(String telephone, Date createTime,
			String verifyCode, String state) {
		this.telephone = telephone;
		this.createTime = createTime;
		this.verifyCode = verifyCode;
		this.state = state;
	}

	// Property accessors

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreatetime(Date createTime) {
		this.createTime = createTime;
	}

	public String getVerifyCode() {
		return this.verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}