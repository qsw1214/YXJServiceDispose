package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.pojo.ProdTags;
import com.youxianji.pojo.ProductTags;

public class GetProductTagsResponseBean {
	
	private String userid;
	private String telephone;
	private String prodid;
	private String tagid;
	private String typeid;
	private String tagname;
	private List<ProductTags> productTagsList;
	
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
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public List<ProductTags> getProductTagsList() {
		return productTagsList;
	}
	public void setProductTagsList(List<ProductTags> productTagsList) {
		this.productTagsList = productTagsList;
	}
	
	
}
