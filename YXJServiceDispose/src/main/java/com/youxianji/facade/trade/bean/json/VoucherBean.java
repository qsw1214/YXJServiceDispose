package com.youxianji.facade.trade.bean.json;

public class VoucherBean {

	private String ticketid;//	String	红包id
	private String tickettitle;//	String	红包标题
	private String value;//	Double	红包金额
	private String datebegin;//	String	有效期开始时间
	private String dateend;//	String	有效期结束时间
	private String describ;//	String	红包描述（使用规则）
	public String getTicketid() {
		return ticketid;
	}
	public void setTicketid(String ticketid) {
		this.ticketid = ticketid;
	}
	
	public String getTickettitle() {
		return tickettitle;
	}
	public void setTickettitle(String tickettitle) {
		this.tickettitle = tickettitle;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDatebegin() {
		return datebegin;
	}
	public void setDatebegin(String datebegin) {
		this.datebegin = datebegin;
	}
	public String getDateend() {
		return dateend;
	}
	public void setDateend(String dateend) {
		this.dateend = dateend;
	}
	public String getDescrib() {
		return describ;
	}
	public void setDescrib(String describ) {
		this.describ = describ;
	}
	
	
	
	
}
