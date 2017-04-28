package com.youxianji.facade.trade.bean.json;

public class OrderDetailBean {

	private String prodnum;//	String	产品编号	
	private String prodname;//	String	产品名称	
	private String quantity;//	Integer	产品数量	
	private String unitprice;//	Double	单个销售价格	
	private String sellprice;//	Double	销售总价	
	private String imageurl;//	String	商品图片地址	
	private String sellflag;//String	销售标记	1.现售2.预售
	private String preselltime;//	String	预售时间 	以天为单位
	
	
	
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
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
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
	
	
	
	
	
	
}
