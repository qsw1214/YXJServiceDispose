package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3002",comment="获取商品类别")
public class GetProdTypeListRequestBean extends BaseRequest{
	
	private String uptypeid;//	String 	上级商品类别ID	为空时获取一级类别

	public String getUptypeid() {
		return uptypeid;
	}

	public void setUptypeid(String uptypeid) {
		this.uptypeid = uptypeid;
	}
	
	
	
	
}
