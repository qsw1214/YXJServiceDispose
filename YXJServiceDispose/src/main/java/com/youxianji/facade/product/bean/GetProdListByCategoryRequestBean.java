package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3003",comment="按类别获取商品列表")
public class GetProdListByCategoryRequestBean extends BaseRequest{
	
	private String typeid;//	String 	商品类别ID
	private String typelevel;//	String	类别级别
	private String page;//	Integer 	页号
	
	
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypelevel() {
		return typelevel;
	}
	public void setTypelevel(String typelevel) {
		this.typelevel = typelevel;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	
	
	
	
}
