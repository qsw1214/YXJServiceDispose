package com.youxianji.facade.bargain.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="k006",comment="立即购买接口")
@Scope("prototype")
public class BuyOrderNowRequestBean extends BaseRequest{

	private String bargainid;
	private String prodid;
	private String paytype;
	private String addressid;//收货地址信息ID
	private String groupbuyid;//团购ID
	private String quantity;
	private String invitecode;
	
	
	
	
	
	
	
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(String groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	public String getAddressid() {
		return addressid;
	}
	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getBargainid() {
		return bargainid;
	}
	public void setBargainid(String bargainid) {
		this.bargainid = bargainid;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	
	
	
}
