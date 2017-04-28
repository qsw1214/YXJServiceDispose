package com.youxianji.facade.groupbuy.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3015",comment="获取商品详情")
public class GetGroupbuyProdDetailRequestBean extends BaseRequest{
	
	private String prodid;

	public String getProdid() {
		return prodid;
	}

	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	
	
	
	
}
