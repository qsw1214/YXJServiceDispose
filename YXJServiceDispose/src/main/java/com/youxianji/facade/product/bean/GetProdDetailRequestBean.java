package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3004",comment="获取商品详情")
public class GetProdDetailRequestBean extends BaseRequest{
	
	private String prodid;

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	
	
	
	
}
