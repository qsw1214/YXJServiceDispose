package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.ProdTypeBean;


public class GetProdTypeListResponseBean{

	private List<ProdTypeBean> typelist;
	
	
	public void setTypelist(List<ProdTypeBean> typelist) {
		this.typelist = typelist;
	}


	public List<ProdTypeBean> getTypelist() {
		return typelist;
	}
	
	

	
}
