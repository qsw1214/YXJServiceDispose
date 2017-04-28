package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.pojo.FreshmanProduct;

public class GetProductByTagResponseBean {
	
	private String userid;
	private String prodid;
	private String telephone;
	private String prodname;
	private String prodUnit;
	private String valueprice;
	private String freshmanprice;
	private String imageUrl;
	private String preSellflag;
	private String preSellTime;
	private String isactivity;
	private String limitquantity;
	private String buytype;
	private String typeid;
	private String depth;
	private String typename;
	private List<Object> freshmanList;
	private FreshmanProduct freshmanProduct;
	private Integer prodstock;//商品库存',
	private String cpackage;//'商品包装',
	
	public Integer getProdstock() {
		return prodstock;
	}
	public void setProdstock(Integer prodstock) {
		this.prodstock = prodstock;
	}
	public String getCpackage() {
		return cpackage;
	}
	public void setCpackage(String cpackage) {
		this.cpackage = cpackage;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public String getValueprice() {
		return valueprice;
	}
	public void setValueprice(String valueprice) {
		this.valueprice = valueprice;
	}
	public String getFreshmanprice() {
		return freshmanprice;
	}
	public void setFreshmanprice(String freshmanprice) {
		this.freshmanprice = freshmanprice;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getPreSellflag() {
		return preSellflag;
	}
	public void setPreSellflag(String preSellflag) {
		this.preSellflag = preSellflag;
	}
	public String getPreSellTime() {
		return preSellTime;
	}
	public void setPreSellTime(String preSellTime) {
		this.preSellTime = preSellTime;
	}
	public String getIsactivity() {
		return isactivity;
	}
	public void setIsactivity(String isactivity) {
		this.isactivity = isactivity;
	}
	public String getLimitquantity() {
		return limitquantity;
	}
	public void setLimitquantity(String limitquantity) {
		this.limitquantity = limitquantity;
	}
	public String getBuytype() {
		return buytype;
	}
	public void setBuytype(String buytype) {
		this.buytype = buytype;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getDepth() {
		return depth;
	}
	public void setDepth(String depth) {
		this.depth = depth;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public List<Object> getFreshmanList() {
		return freshmanList;
	}
	public void setFreshmanList(List<Object> freshmanList) {
		this.freshmanList = freshmanList;
	}
	public FreshmanProduct getFreshmanProduct() {
		return freshmanProduct;
	}
	public void setFreshmanProduct(FreshmanProduct freshmanProduct) {
		this.freshmanProduct = freshmanProduct;
	}
	
}
