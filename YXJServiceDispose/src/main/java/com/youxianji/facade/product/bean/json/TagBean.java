package com.youxianji.facade.product.bean.json;

import java.util.List;

public class TagBean {
	
	private String tagid;
	private String tagname;
	private String tagurl;
	private List<ProdInfoBean> prodlist;
	
	
	
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String getTagname() {
		return tagname;
	}
	public void setTagname(String tagname) {
		this.tagname = tagname;
	}
	public String getTagurl() {
		return tagurl;
	}
	public void setTagurl(String tagurl) {
		this.tagurl = tagurl;
	}
	public List<ProdInfoBean> getProdlist() {
		return prodlist;
	}
	public void setProdlist(List<ProdInfoBean> prodlist) {
		this.prodlist = prodlist;
	}
	
	
	

	
    

}
