package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class BusinessUserBills {

	private String bbillid; 	//'账务ID',
	private String buserid;   //'商户ID',
	private String operatesn;  //'操作编号',
	private Date operatetime;  //'操作时间',
	private BigDecimal amountbalance; //'操作后额度',`
	private BigDecimal couponBalance;//'代金券账户余额'
	public BigDecimal getCouponBalance() {
		return couponBalance;
	}
	public void setCouponBalance(BigDecimal couponBalance) {
		this.couponBalance = couponBalance;
	}
	private BigDecimal operatemoney;  //'操作金额',
	private String billsflag;  //'账务标记 0.存入 1.支出',
	private String operatedesc; //'操作描述',
	private String remark;   //'备注',
	
	public String getBbillid() {
		return bbillid;
	}
	public void setBbillid(String bbillid) {
		this.bbillid = bbillid;
	}
	public String getBuserid() {
		return buserid;
	}
	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}
	public String getOperatesn() {
		return operatesn;
	}
	public void setOperatesn(String operatesn) {
		this.operatesn = operatesn;
	}
	public Date getOperatetime() {
		return operatetime;
	}
	public void setOperatetime(Date operatetime) {
		this.operatetime = operatetime;
	}
	public BigDecimal getAmountbalance() {
		return amountbalance;
	}
	public void setAmountbalance(BigDecimal amountbalance) {
		this.amountbalance = amountbalance;
	}
	public BigDecimal getOperatemoney() {
		return operatemoney;
	}
	public void setOperatemoney(BigDecimal operatemoney) {
		this.operatemoney = operatemoney;
	}
	public String getBillsflag() {
		return billsflag;
	}
	public void setBillsflag(String billsflag) {
		this.billsflag = billsflag;
	}
	public String getOperatedesc() {
		return operatedesc;
	}
	public void setOperatedesc(String operatedesc) {
		this.operatedesc = operatedesc;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
}
