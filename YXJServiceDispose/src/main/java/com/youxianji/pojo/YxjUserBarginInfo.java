package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class YxjUserBarginInfo {
	private String id;//主键
	private String rulesId;//砍价规则ID
	private String userId;//用户ID
	 private BigDecimal totalMoney;//L累计砍价金额
	private String remark;//备注信息
	private Date createTime;//创建时间
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getRulesId() {
		return rulesId;
	}
	public void setRulesId(String rulesId) {
		this.rulesId = rulesId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public BigDecimal getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
}
