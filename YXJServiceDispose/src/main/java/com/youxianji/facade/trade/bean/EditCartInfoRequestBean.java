package com.youxianji.facade.trade.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="4002",comment="编辑购物车BEAN")
public class EditCartInfoRequestBean extends BaseRequest{
	private String configflag;//	String	设置 标识	1.添加2.修改 3.删除 4.全部删除
	private String cartid;//	String	地址ID	添加时传空
	private String prodid;//	String	商品ID	
	private String quantity;//String	购买数量	
	public String getConfigflag() {
		return configflag;
	}
	public void setConfigflag(String configflag) {
		this.configflag = configflag;
	}
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	
	
	
}
