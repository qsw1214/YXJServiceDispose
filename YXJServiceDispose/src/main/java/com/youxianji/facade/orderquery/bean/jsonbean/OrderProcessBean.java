package com.youxianji.facade.orderquery.bean.jsonbean;

public class OrderProcessBean {

	private String ordersn;//订单号
	private String processdesc;//流程描述
	private String processtime ;//流程处理时间
	private String processremark;//流程备注
	
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public String getProcessdesc() {
		return processdesc;
	}
	public void setProcessdesc(String processdesc) {
		this.processdesc = processdesc;
	}
	public String getProcesstime() {
		return processtime;
	}
	public void setProcesstime(String processtime) {
		this.processtime = processtime;
	}
	public String getProcessremark() {
		return processremark;
	}
	public void setProcessremark(String processremark) {
		this.processremark = processremark;
	}
	
}
