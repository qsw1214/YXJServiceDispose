package com.youxianji.pojo;

import java.util.Date;

public class MemberCardInfo {

	private String cardid;//'会员卡ID',
	private Integer cardvalue;//'会员卡面值',
	private Integer sellprice;//'会员卡实际售价',
	private Integer chargeamount;//'实际充入额度',
	private String remark;//'备注',
	private String carddesc;//'规则描述'
	private String state;//'0.无效  1.有效'
	private Date createTime;
	private String createUser;
	
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getCarddesc() {
		return carddesc;
	}
	public void setCarddesc(String carddesc) {
		this.carddesc = carddesc;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public Integer getCardvalue() {
		return cardvalue;
	}
	public void setCardvalue(Integer cardvalue) {
		this.cardvalue = cardvalue;
	}
	public Integer getSellprice() {
		return sellprice;
	}
	public void setSellprice(Integer sellprice) {
		this.sellprice = sellprice;
	}
	
	public Integer getChargeamount() {
		return chargeamount;
	}
	public void setChargeamount(Integer chargeamount) {
		this.chargeamount = chargeamount;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	
	
	
	
}
