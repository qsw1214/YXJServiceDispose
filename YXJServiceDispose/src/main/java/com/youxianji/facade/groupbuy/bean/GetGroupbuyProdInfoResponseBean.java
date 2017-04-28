package com.youxianji.facade.groupbuy.bean;

import java.util.List;

import com.youxianji.facade.groupbuy.bean.json.GroupbuyProdInfoBean;

public class GetGroupbuyProdInfoResponseBean {
	private String groupbuyid;
	private String isenable;//	String	是否存在活动 0不存在 1已存在	
	private List<GroupbuyProdInfoBean> prodlist;
	public String getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(String groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	public List<GroupbuyProdInfoBean> getProdlist() {
		return prodlist;
	}
	public void setProdlist(List<GroupbuyProdInfoBean> prodlist) {
		this.prodlist = prodlist;
	}
	public String getIsenable() {
		return isenable;
	}
	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
	
	
}
