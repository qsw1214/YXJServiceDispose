package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.facade.product.bean.json.ProdTypeBean;


public class GetHomeProdListResponseBean{

	private List<ProdInfoBean> prodlist;
	private List<ProdTypeBean> typelist;
	
	
	public List<ProdInfoBean> getProdlist() {
		return prodlist;
	}
	public void setProdlist(List<ProdInfoBean> prodlist) {
		this.prodlist = prodlist;
	}
	public List<ProdTypeBean> getTypelist() {
		return typelist;
	}
	public void setTypelist(List<ProdTypeBean> typelist) {
		this.typelist = typelist;
	}

	
}
