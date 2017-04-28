package com.youxianji.facade.trade.bean;

import java.util.List;

import com.youxianji.facade.trade.bean.json.OrderDetailBean;
import com.youxianji.facade.trade.bean.json.ReceiveAddrBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;


public class SettleCartInfoResponseBean{
	private String ordersn;//	String	订单号
	private String deliverfee;//	String	订单配送费
	private String servicefee;//	String	订单服务费
	private String discountamt;//	String	总优惠金额
	private String sellprice;//	String	实际支付总金额
	private String useticketcount;
	private String payflag;//	String	配送标记	1配送2自提
	private String sendday;//	String	配送天	1 明天2后天
	private String specificday;
	private List<OrderDetailBean> detaillist;//	JSON字符串	商品详细
	private ReceiveAddrBean receiveaddress;
	private VoucherBean ticketdetail; //默认使用代金券信息
	private List<VoucherBean> ticketList; //可使用代金券列表
	
	
	
	
	
	public List<VoucherBean> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<VoucherBean> ticketList) {
		this.ticketList = ticketList;
	}
	public String getSpecificday() {
		return specificday;
	}
	public void setSpecificday(String specificday) {
		this.specificday = specificday;
	}
	public String getPayflag() {
		return payflag;
	}
	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}
	public String getSendday() {
		return sendday;
	}
	public void setSendday(String sendday) {
		this.sendday = sendday;
	}
	public String getUseticketcount() {
		return useticketcount;
	}
	public void setUseticketcount(String useticketcount) {
		this.useticketcount = useticketcount;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public ReceiveAddrBean getReceiveaddress() {
		return receiveaddress;
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
	public List<OrderDetailBean> getDetaillist() {
		return detaillist;
	}
	public void setDetaillist(List<OrderDetailBean> detaillist) {
		this.detaillist = detaillist;
	}
	public VoucherBean getTicketdetail() {
		return ticketdetail;
	}
	public void setTicketdetail(VoucherBean ticketdetail) {
		this.ticketdetail = ticketdetail;
	}
	public void setReceiveaddress(ReceiveAddrBean receiveaddress) {
		this.receiveaddress = receiveaddress;
	}

	
	
	
}
