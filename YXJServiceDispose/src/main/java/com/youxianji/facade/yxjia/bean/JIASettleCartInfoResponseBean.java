package com.youxianji.facade.yxjia.bean;

import java.util.List;

import com.youxianji.facade.yxjia.bean.json.JIAOrderDetailBean;
import com.youxianji.facade.yxjia.bean.json.JIAReceiveAddrBean;


public class JIASettleCartInfoResponseBean{
	private String ordersn;//	String	订单号
	private String deliverfee;//	String	订单配送费
	private String servicefee;//	String	订单服务费
	private String discountamt;//	String	总优惠金额
	private String sellprice;//	String	实际支付总金额
	private String prodtotalprice;
	private String payflag;//	String	配送标记	1配送2自提
	private String specificday;
	private List<JIAOrderDetailBean> detaillist;//	JSON字符串	商品详细
	private JIAReceiveAddrBean receiveaddress;
	
	
	
	public String getProdtotalprice() {
		return prodtotalprice;
	}
	public void setProdtotalprice(String prodtotalprice) {
		this.prodtotalprice = prodtotalprice;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getDeliverfee() {
		return deliverfee;
	}
	public void setDeliverfee(String deliverfee) {
		this.deliverfee = deliverfee;
	}
	public String getServicefee() {
		return servicefee;
	}
	public void setServicefee(String servicefee) {
		this.servicefee = servicefee;
	}
	public String getDiscountamt() {
		return discountamt;
	}
	public void setDiscountamt(String discountamt) {
		this.discountamt = discountamt;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getPayflag() {
		return payflag;
	}
	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}
	public String getSpecificday() {
		return specificday;
	}
	public void setSpecificday(String specificday) {
		this.specificday = specificday;
	}
	public List<JIAOrderDetailBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<JIAOrderDetailBean> detaillist) {
		this.detaillist = detaillist;
	}
	public JIAReceiveAddrBean getReceiveaddress() {
		return receiveaddress;
	}
	public void setReceiveaddress(JIAReceiveAddrBean receiveaddress) {
		this.receiveaddress = receiveaddress;
	}
	
	
	
	
	
	
	
	
}
