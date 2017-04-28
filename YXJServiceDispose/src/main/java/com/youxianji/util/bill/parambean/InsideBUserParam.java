package com.youxianji.util.bill.parambean;

import java.math.BigDecimal;

public class InsideBUserParam {

	private String billid ;// '账单ID',
	private String buserId;//'商户ID',
	private String operatesn ;//操作编号',
	private BigDecimal operatemoney ;// '操作金额',
	private BigDecimal amountbalance ;//'账户额度余额',
	private String billsflag  ;//'账务标记 0.存入 1.支出 ,
	private String operatedesc ;// '操作描述',
	private String remark  ;//'备注',
	public String getBillid() {
		return billid;
	}
	public void setBillid(String billid) {
		this.billid = billid;
	}
	
	public String getBuserId() {
		return buserId;
	}
	public void setBuserId(String buserId) {
		this.buserId = buserId;
	}
	public String getOperatesn() {
		return operatesn;
	}
	public void setOperatesn(String operatesn) {
		this.operatesn = operatesn;
	}
	public BigDecimal getOperatemoney() {
		return operatemoney;
	}
	public void setOperatemoney(BigDecimal operatemoney) {
		this.operatemoney = operatemoney;
	}
	public BigDecimal getAmountbalance() {
		return amountbalance;
	}
	public void setAmountbalance(BigDecimal amountbalance) {
		this.amountbalance = amountbalance;
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
