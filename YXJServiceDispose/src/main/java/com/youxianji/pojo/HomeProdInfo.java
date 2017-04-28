package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 首页商品列表
 * */
public class HomeProdInfo{

	private String prodnum;// 商品编号
	private String prodname;//商品名称
	private String unit;//商品售出单位
	private String valueprice;//商品原价
	private String sellprice;//商品会员价
	private String imageurl;//商品默认图片地址
	private String isactivity;//是否有活动 	 1有活动0无活动
	private String limitquantity;// 活动限购数量  -1表示不限数量 
	private String buytype; //限购商品类型  1.抢购商品 2.单笔限量
	private String sellflag ;//销售标记 1.现售2.预售
	private Date preselltime ;//预售时间 
	public String getProdnum() {
		return prodnum;
	}
	public void setProdnum(String prodnum) {
		this.prodnum = prodnum;
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
	public Date getPreselltime() {
		return preselltime;
	}
	public void setPreselltime(Date preselltime) {
		this.preselltime = preselltime;
	}
	
	
	
	
}
