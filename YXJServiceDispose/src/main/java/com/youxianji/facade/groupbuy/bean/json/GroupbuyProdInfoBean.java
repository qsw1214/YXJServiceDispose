package com.youxianji.facade.groupbuy.bean.json;

import java.util.List;

import com.youxianji.pojo.ProdTags;

public class GroupbuyProdInfoBean {

	private String prodid;//;//	商品ID	
	private String prodname;//	商品名称	
	private String unit;//	商品售出单位	
	private String valueprice;//	商品原价	
	private String sellprice;//	商品会员价	
	private String groupbuyprice;//	商品团购价
	private String freshprice;
	private String freshflag;
	private String imageurl;//	商品默认图片地址	
	private String isactivity;//	Boolean	是否有活动	
	private String limitquantity;//	活动限购数量	-1表示不限数量
	private String buytype;//	限购商品类型	1.抢购商品2.单笔限量
	private String sellflag;//	销售标记	1.现售2.预售
	private String preselltime;//	预售时间 	以天为单位
	private String proddesc;
	private Integer prodstock;//商品库存',
	private String cpackage;//'商品包装',
	private List<ProdTags> prodTagsList;//商品显示标签
	private String ishome;//是否为首页 0. 不是 1.是
	private String specialFlag;//是否特惠 0 不是 1是
	
	
	public String getSpecialFlag() {
		return specialFlag;
	}
	public void setSpecialFlag(String specialFlag) {
		this.specialFlag = specialFlag;
	}
	public String getIshome() {
		return ishome;
	}
	public void setIshome(String ishome) {
		this.ishome = ishome;
	}
	private String detailurl;

	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public List<ProdTags> getProdTagsList() {
		return prodTagsList;
	}
	public void setProdTagsList(List<ProdTags> prodTagsList) {
		this.prodTagsList = prodTagsList;
	}
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
	public String getFreshflag() {
		return freshflag;
	}
	public void setFreshflag(String freshflag) {
		this.freshflag = freshflag;
	}
	public String getFreshprice() {
		return freshprice;
	}
	public void setFreshprice(String freshprice) {
		this.freshprice = freshprice;
	}
	public String getProddesc() {
		return proddesc;
	}
	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getValueprice() {
		return valueprice;
	}
	public void setValueprice(String valueprice) {
		this.valueprice = valueprice;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getImageurl() {
		return imageurl;
	}
	public void setImageurl(String imageurl) {
		this.imageurl = imageurl;
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
	public String getSellflag() {
		return sellflag;
	}
	public void setSellflag(String sellflag) {
		this.sellflag = sellflag;
	}
	public String getPreselltime() {
		return preselltime;
	}
	public void setPreselltime(String preselltime) {
		this.preselltime = preselltime;
	}
	public String getGroupbuyprice() {
		return groupbuyprice;
	}
	public void setGroupbuyprice(String groupbuyprice) {
		this.groupbuyprice = groupbuyprice;
	}
	
	
}
