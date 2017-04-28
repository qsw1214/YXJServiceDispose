package com.youxianji.facade.orderquery.bean;

import java.util.List;

import com.youxianji.facade.orderquery.bean.jsonbean.OrderDetailData;
import com.youxianji.facade.orderquery.bean.jsonbean.PageOrderData;

public class GetOrderDetailResponseBean {

	private String ordersn;//	String	订单号	
	private String ordertime;//	String	订单时间	
	private String deliverfee;//	String	订单配送费	
	private String servicefee;//	String	订单服务费	
	private String payflag;//	String	支付标记	1. 微信支付，2. 支付宝支付，3.余额支付
	private String state;//	String	订单状态	 1.待付款 2.已支付 3.已发货  4.已到货 5.订单已取消 6.退货中 7.已退货 
	private String receivephone;//	String	收货人手机号	
	private String receivename;//	String	收货人姓名	
	private String receiveaddress;//	String	收货人地址	
	private String receivetime;//	String	收货时间	
	private String sendername;//	String	送货人姓名	
	private String senderphone;//	String	送货人手机号	
	private String remark;//	String	订单备注	
	private String isreview;//	String	是否评价	0.未评价1已评价
	private String totalprice;//	String	商品销售总金额	
	private String issprcial;//	String	是否使用代金券	0.未使用 1.已使用
	private String specialamt;//	String	使用代金券金额	
	private String deductamt;//	String	满减总金额	
	private String firstamt;//	String	首单立减金额	
	private String sellprice;//	Double	实际支付总金额	
	private List<OrderDetailData> detaillist;//	JSON字符串	订单明细列表
	private String cargocode;
	private String totaldiscount;
	private String receiveprovince;//	String 收货省份
	private String receivecity;//	String 收货城市
	private String receivedistrict;//	String 收货区县

	
	
	
	public String getReceiveprovince() {
		return receiveprovince;
	}
	public void setReceiveprovince(String receiveprovince) {
		this.receiveprovince = receiveprovince;
	}
	public String getReceivecity() {
		return receivecity;
	}
	public void setReceivecity(String receivecity) {
		this.receivecity = receivecity;
	}
	public String getReceivedistrict() {
		return receivedistrict;
	}
	public void setReceivedistrict(String receivedistrict) {
		this.receivedistrict = receivedistrict;
	}
	public String getTotaldiscount() {
		return totaldiscount;
	}
	public void setTotaldiscount(String totaldiscount) {
		this.totaldiscount = totaldiscount;
	}
	public String getCargocode() {
		return cargocode;
	}
	public void setCargocode(String cargocode) {
		this.cargocode = cargocode;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
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
	public String getPayflag() {
		return payflag;
	}
	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getReceivephone() {
		return receivephone;
	}
	public void setReceivephone(String receivephone) {
		this.receivephone = receivephone;
	}
	public String getReceivename() {
		return receivename;
	}
	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}
	public String getReceiveaddress() {
		return receiveaddress;
	}
	public void setReceiveaddress(String receiveaddress) {
		this.receiveaddress = receiveaddress;
	}
	public String getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}
	public String getSendername() {
		return sendername;
	}
	public void setSendername(String sendername) {
		this.sendername = sendername;
	}
	public String getSenderphone() {
		return senderphone;
	}
	public void setSenderphone(String senderphone) {
		this.senderphone = senderphone;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getIsreview() {
		return isreview;
	}
	public void setIsreview(String isreview) {
		this.isreview = isreview;
	}
	public String getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}
	public String getIssprcial() {
		return issprcial;
	}
	public void setIssprcial(String issprcial) {
		this.issprcial = issprcial;
	}
	public String getSpecialamt() {
		return specialamt;
	}
	public void setSpecialamt(String specialamt) {
		this.specialamt = specialamt;
	}
	public String getDeductamt() {
		return deductamt;
	}
	public void setDeductamt(String deductamt) {
		this.deductamt = deductamt;
	}
	public String getFirstamt() {
		return firstamt;
	}
	public void setFirstamt(String firstamt) {
		this.firstamt = firstamt;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public List<OrderDetailData> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<OrderDetailData> detaillist) {
		this.detaillist = detaillist;
	}
	
	
}
