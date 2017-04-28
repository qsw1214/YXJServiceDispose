package com.youxianji.facade.orderquery.bean.jsonbean;

public class PageBillsData {

	/* 账单时间 */
	private String ordertime;
	
	/* 账单金额 */
	private String money;
	
	/* 操作后余额 */
	private String balance;
	
	/* 交易号 */
	private String ordersn;
	
	/* 账单类型 0.支出 1.存入 */
	private String billtype;
	
	/* 操作标记 */
	private String operateflag;

	public String getOrdertime() {
		return ordertime;
	}

	public void setOrdertime(String ordertime) {
		this.ordertime = ordertime;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getOrdersn() {
		return ordersn;
	}

	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getOperateflag() {
		return operateflag;
	}

	public void setOperateflag(String operateflag) {
		this.operateflag = operateflag;
	}
	
}
