package com.youxianji.facade.yxjia.bean;

import java.util.List;

import com.youxianji.facade.yxjia.bean.json.JIAProdInfoBean;
import com.youxianji.facade.yxjia.bean.json.JIAProdTypeBean;


public class JIAGetHomeProdListResponseBean{

	private List<JIAProdInfoBean> prodlist;
	private List<JIAProdTypeBean> typelist;
	public List<JIAProdInfoBean> getProdlist() {
		return prodlist;
	}
	public void setProdlist(List<JIAProdInfoBean> prodlist) {
		this.prodlist = prodlist;
	}
	public List<JIAProdTypeBean> getTypelist() {
		return typelist;
	}
	public void setTypelist(List<JIAProdTypeBean> typelist) {
		this.typelist = typelist;
	}
	
	

	
}
