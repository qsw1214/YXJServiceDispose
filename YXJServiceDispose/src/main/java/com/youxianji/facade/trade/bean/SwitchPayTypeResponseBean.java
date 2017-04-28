package com.youxianji.facade.trade.bean;

import java.util.List;

import com.youxianji.facade.trade.bean.json.OrderDetailBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;


public class SwitchPayTypeResponseBean{
	private String deliverfee;//	String	配送费	
	private String discountamt;//	String	总优惠金额	
	private String useticketcount;
	private String sellprice;
	private VoucherBean ticketdetail; //JSON	默认使用代金券详细	(同4003)
	private List<VoucherBean> ticketList; //JSON	可使用代金券列表	(同4003)
	public List<VoucherBean> getTicketList() {
		return ticketList;
	}
	public void setTicketList(List<VoucherBean> ticketList) {
		this.ticketList = ticketList;
	}
	private List<OrderDetailBean> detaillist;//	JSON字符串	商品详细
	
	
	
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
	public String getUseticketcount() {
		return useticketcount;
	}
	public void setUseticketcount(String useticketcount) {
		this.useticketcount = useticketcount;
	}
	public String getDeliverfee() {
		return deliverfee;
	}
	public void setDeliverfee(String deliverfee) {
		this.deliverfee = deliverfee;
	}
	public String getDiscountamt() {
		return discountamt;
	}
	public void setDiscountamt(String discountamt) {
		this.discountamt = discountamt;
	}
	public VoucherBean getTicketdetail() {
		return ticketdetail;
	}
	public void setTicketdetail(VoucherBean ticketdetail) {
		this.ticketdetail = ticketdetail;
	}

    

}
