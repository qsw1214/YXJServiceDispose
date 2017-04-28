package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3007",comment="获取商品标签")
public class GetProductTagsRequestBean extends BaseRequest{
	
	private String userid;
	private String telephone;
	private String prodid;
	private String tagid;//商品分类标签
	private String tagsId;//商品显示标签
	
	public String getTagsId() {
		return tagsId;
	}
	public void setTagsId(String tagsId) {
		this.tagsId = tagsId;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	
}
