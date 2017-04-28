package com.youxianji.facade.orderquery.bean.jsonbean;

public class WaitPayOrderBean {
	
	private String baseordersn;//	String	总订单号	
	private String amount;//订单金额（实际支付价格）
	private String ordertime;//	String	订单时间	
	private String cargocode;//收货确认码
	private String orderstate;//订单状态
	private String prodquantity;//商品数量
	private String prodimageurl;//商品图片
	private String paytype;//支付方式
	
	public String getBaseordersn() {
		return baseordersn;
	}
	public void setBaseordersn(String baseordersn) {
		this.baseordersn = baseordersn;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}
	public String getCargocode() {
		return cargocode;
	}
	public void setCargocode(String cargocode) {
		this.cargocode = cargocode;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getProdquantity() {
		return prodquantity;
	}
	public void setProdquantity(String prodquantity) {
		this.prodquantity = prodquantity;
	}
	public String getProdimageurl() {
		return prodimageurl;
	}
	public void setProdimageurl(String prodimageurl) {
		this.prodimageurl = prodimageurl;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	
	
}
