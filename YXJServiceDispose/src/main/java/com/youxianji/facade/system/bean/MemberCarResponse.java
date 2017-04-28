package com.youxianji.facade.system.bean;

public class MemberCarResponse {
	private String cardid;//会员卡id
	private String cardvalue;//会员卡面值
	private String remark;//会员卡备注
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCardvalue() {
		return cardvalue;
	}
	public void setCardvalue(String cardvalue) {
		this.cardvalue = cardvalue;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


}
