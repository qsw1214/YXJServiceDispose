package com.youxianji.facade.orderquery.bean.jsonbean;

public class OrderDetailData {

	private String detailnum;//	String	订单明细编号	
	private String prodnum;//	String	商品编号	
	private String prodid;// 商品ID
	private String imgurl;//	String	商品图片	
	private String isreview;//	String	是否评价	1.未评价2已评价
	private String prodname;//	String	产品名称	
	private String unitprice;//	String	产品单价	
	private String quantity;//	Integer	购买数量	
	private String totalprice;//	Double	购买总价	z
	private String preselltag;
	private String preselltime;
	
	
	
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getPreselltag() {
		return preselltag;
	}
	public void setPreselltag(String preselltag) {
		this.preselltag = preselltag;
	}
	public String getPreselltime() {
		return preselltime;
	}
	public void setPreselltime(String preselltime) {
		this.preselltime = preselltime;
	}
	public String getDetailnum() {
		return detailnum;
	}
	public void setDetailnum(String detailnum) {
		this.detailnum = detailnum;
	}
	public String getProdnum() {
		return prodnum;
	}
	public void setProdnum(String prodnum) {
		this.prodnum = prodnum;
	}
	public String getImgurl() {
		return imgurl;
	}
	public void setImgurl(String imgurl) {
		this.imgurl = imgurl;
	}
	public String getIsreview() {
		return isreview;
	}
	public void setIsreview(String isreview) {
		this.isreview = isreview;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getUnitprice() {
		return unitprice;
	}
	public void setUnitprice(String unitprice) {
		this.unitprice = unitprice;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	
	
	
}
