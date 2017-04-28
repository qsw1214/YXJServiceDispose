package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.ProdInfoBean;

public class GetFreshmanProductResponseBean {
	
	 private List<ProdInfoBean> prodlist;

	public List<ProdInfoBean> getProdlist() {
		return prodlist;
	}

	public void setProdlist(List<ProdInfoBean> prodlist) {
		this.prodlist = prodlist;
	}
	    
}
