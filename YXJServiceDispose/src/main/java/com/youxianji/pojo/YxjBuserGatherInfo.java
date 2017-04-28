package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;



public class YxjBuserGatherInfo {

	 private String gatherOrdersn;// varchar(50) COLLATE utf8_bin NOT NULL COMMENT 'B类商户收款单号',
	 private BigDecimal gatherMoney;// decimal(12,2) NOT NULL COMMENT 'B类商户收款金额',
	 private Date gatherTime;// datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT 'B类商户收款时间',
	 private String buserUserid;
	 private String cuserUserid;// varchar(50) COLLATE utf8_bin NOT NULL COMMENT '付款C类商户ID',
	 private String gatherState;// char(1) COLLATE utf8_bin NOT NULL COMMENT '收款状态 1.成功',
	 private String gatherRemark;// varchar(300) COLLATE utf8_bin DEFAULT NULL,
	public String getGatherOrdersn() {
		return gatherOrdersn;
	}
	public void setGatherOrdersn(String gatherOrdersn) {
		this.gatherOrdersn = gatherOrdersn;
	}
	public BigDecimal getGatherMoney() {
		return gatherMoney;
	}
	public void setGatherMoney(BigDecimal gatherMoney) {
		this.gatherMoney = gatherMoney;
	}
	public Date getGatherTime() {
		return gatherTime;
	}
	public void setGatherTime(Date gatherTime) {
		this.gatherTime = gatherTime;
	}
	public String getBuserUserid() {
		return buserUserid;
	}
	public void setBuserUserid(String buserUserid) {
		this.buserUserid = buserUserid;
	}
	public String getCuserUserid() {
		return cuserUserid;
	}
	public void setCuserUserid(String cuserUserid) {
		this.cuserUserid = cuserUserid;
	}
	public String getGatherState() {
		return gatherState;
	}
	public void setGatherState(String gatherState) {
		this.gatherState = gatherState;
	}
	public String getGatherRemark() {
		return gatherRemark;
	}
	public void setGatherRemark(String gatherRemark) {
		this.gatherRemark = gatherRemark;
	}


}
