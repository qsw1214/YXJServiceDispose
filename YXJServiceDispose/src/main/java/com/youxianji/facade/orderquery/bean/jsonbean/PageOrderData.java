package com.youxianji.facade.orderquery.bean.jsonbean;

public class PageOrderData {

	/* 订单号 */
	private String ordersn;
	
	/* 订单金额 */
	private String amount;
	
	/* 订单时间 */
	private String ordertime;
	
	private String cargocode;//	String	收货确认码	
	private String orderstate;//	String	订单状态	订单状态 0.初始 1.待付款 2.已支付 3.已发货 4.已到货 5.订单取消 6.退货中 7.已退货
    private String prodquantity;
    private String prodimageurl;
    private String paytype;// String 支付方式 1. 微信支付  2.支付宝支付 3.余额支付
    private String baseordersn;//总订单号
    
    
    
    
	public String getPaytype() {
		return paytype;
	}

	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}

	public String getProdimageurl() {
		return prodimageurl;
	}

	public void setProdimageurl(String prodimageurl) {
		this.prodimageurl = prodimageurl;
	}

	public String getProdquantity() {
		return prodquantity;
	}

	public void setProdquantity(String prodquantity) {
		this.prodquantity = prodquantity;
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

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
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

	public String getBaseordersn() {
		return baseordersn;
	}

	public void setBaseordersn(String baseordersn) {
		this.baseordersn = baseordersn;
	}
	
}
