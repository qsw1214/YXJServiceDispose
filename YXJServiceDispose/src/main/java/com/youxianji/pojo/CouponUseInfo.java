package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class CouponUseInfo {

	private String cuid ;//'主键',
	private CouponRuleInfo couponRuleInfo;//'代金券ID',
	private UserInfo userInfo;//用户信息
	private String ordersn ;//'订单号',
	private BigDecimal couponAmount ;//'红包金额',
	private String state;//'使用状态 1.可使用 2.使用中 3,已使用 4.已过期'
	private Date useBeginTime;
	private Date useEndTime;
	private String coupontitle;
	private BigDecimal sinceMoney;//使用金额条件
	private String couponName;//红包名称
	private String couponDesc;//红包描述
	
	

	public BigDecimal getSinceMoney() {
		return sinceMoney;
	}

	public void setSinceMoney(BigDecimal sinceMoney) {
		this.sinceMoney = sinceMoney;
	}

	public String getCouponName() {
		return couponName;
	}

	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}

	public String getCouponDesc() {
		return couponDesc;
	}

	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}

	public String getCoupontitle() {
		return coupontitle;
	}

	public void setCoupontitle(String coupontitle) {
		this.coupontitle = coupontitle;
	}
	
	
	public Date getUseBeginTime() {
		return useBeginTime;
	}
	public void setUseBeginTime(Date useBeginTime) {
		this.useBeginTime = useBeginTime;
	}
	public Date getUseEndTime() {
		return useEndTime;
	}
	public void setUseEndTime(Date useEndTime) {
		this.useEndTime = useEndTime;
	}
	public String getCuid() {
		return cuid;
	}
	public void setCuid(String cuid) {
		this.cuid = cuid;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	
	public CouponRuleInfo getCouponRuleInfo() {
		return couponRuleInfo;
	}
	public void setCouponRuleInfo(CouponRuleInfo couponRuleInfo) {
		this.couponRuleInfo = couponRuleInfo;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	public BigDecimal getCouponAmount() {
		return couponAmount;
	}

	public void setCouponAmount(BigDecimal couponAmount) {
		this.couponAmount = couponAmount;
	}

	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
}
