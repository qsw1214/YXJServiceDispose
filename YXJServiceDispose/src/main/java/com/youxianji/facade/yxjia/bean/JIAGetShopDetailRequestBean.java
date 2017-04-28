package com.youxianji.facade.yxjia.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="8007",comment="JIA-获取的店铺详情")
public class JIAGetShopDetailRequestBean extends BaseRequest{
	
	private String shopid;//	String	店铺ID

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}
	
	
	
	
	
}
