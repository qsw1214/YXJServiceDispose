package com.youxianji.pojo;
import java.math.BigDecimal;

public class GroupbuyDetail {
	
	private String detailid;//团购明细ID
	private String groupbuyid;//团购活动ID
	private String prodid;//砍价商品ID
	private BigDecimal groupbuyPrice;//团购价格
	private String specialFlag;//是否特惠 0 不是 1是
	private String invitecode;
	
	
	
	
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getSpecialFlag() {
		return specialFlag;
	}
	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}
	public String getDetailid() {
		return detailid;
	}
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}
	public String getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(String groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	public BigDecimal getGroupbuyPrice() {
		return groupbuyPrice;
	}
	public void setGroupbuyPrice(BigDecimal groupbuyPrice) {
		this.groupbuyPrice = groupbuyPrice;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	
}
