package com.youxianji.facade.coupon.bean;

import com.youxianji.facade.trade.bean.json.VoucherBean;

public class ReceiveShareCouponResponseBean {

	/* 领取结果 0.领取成功 1.已领过 2.已领完 3.已结束 */
	private String resultflag;
	
	/* 红包信息BEAN */
	private VoucherBean ticketdetail;

	public String getResultflag() {
		return resultflag;
	}

	public void setResultflag(String resultflag) {
		this.resultflag = resultflag;
	}

	public VoucherBean getTicketdetail() {
		return ticketdetail;
	}

	public void setTicketdetail(VoucherBean ticketdetail) {
		this.ticketdetail = ticketdetail;
	}
	
	
}
