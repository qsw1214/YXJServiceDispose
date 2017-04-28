package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3012",comment="添加商品评价")
public class AddProdEvaluateRequestBean extends BaseRequest{
	
	private String prodid;//	String	商品ID	
	private String prodevaluate;//	String	商品评价	
	private String starlevel;//	String	星级	分为1,2,3,4,5
	private String ordersn;
	
	
	
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdevaluate() {
		return prodevaluate;
	}
	public void setProdevaluate(String prodevaluate) {
		this.prodevaluate = prodevaluate;
	}
	public String getStarlevel() {
		return starlevel;
	}
	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}
	
	
	
}
