package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3009",comment="按搜索关键字获取商品列表")
public class GetProdListBySearchNameRequestBean extends BaseRequest{
	
    private String searchword;

	public String getSearchword() {
		return searchword;
	}

	public void setSearchword(String searchword) {
		this.searchword = searchword;
	}
    
    
	
}
