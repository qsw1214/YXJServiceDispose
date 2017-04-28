package com.youxianji.facade.trade.bean.json;

import java.util.List;

import com.youxianji.pojo.ProdTags;

public class CartInfoBean {

	private String cartid;
	private String prodid;//	String	商品编号	
	private String prodname;//	String	商品名称	
	private String unit;//	String	商品售出单位	
	private String valueprice;//	String	商品原价	
	private String sellprice;//	String	商品会员价	
	private String memberprice;// String 
	private String freshflag;//	String	新人专享标志位	1 新人 0 非新人
	private String freshprice;//	String	新人专享价	（新人专享专用）
	private String imageur;//	String	商品默认图片	
	private String sellflag;//	String	销售标记	1.现售2.预售
	private String preselltime;//	String	预售时间 	以天为单位
	private String quantity;//String	购买数量	
	private String totalprice;//	String	销售总价	
	private List<ProdTags> prodTagsList;//商品显示标签
	private String detailurl;
	
	
	
	
	
	
	public String getMemberprice() {
		return memberprice;
	}
	public void setMemberprice(String memberprice) {
		this.memberprice = memberprice;
	}
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
	public String getValueprice() {
		return valueprice;
	}
	public void setValueprice(String valueprice) {
		this.valueprice = valueprice;
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
	public String getCartid() {
		return cartid;
	}
	public void setCartid(String cartid) {
		this.cartid = cartid;
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
	
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getImageur() {
		return imageur;
	}
	public void setImageur(String imageur) {
		this.imageur = imageur;
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
