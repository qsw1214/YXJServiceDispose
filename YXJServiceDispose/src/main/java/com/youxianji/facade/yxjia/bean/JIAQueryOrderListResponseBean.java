package com.youxianji.facade.yxjia.bean;

import java.util.List;

import com.youxianji.facade.orderquery.bean.jsonbean.PageOrderData;

public class JIAQueryOrderListResponseBean {

	/* 总记录数 */
	private String totalcount;
	
	/* 总页数 */
	private String totalpage;
	
	/* 当前页 */
	private String currentpage;
	
	/* 每页条数 */
	private String pagecount;
	
	/* 订单列表 */
	private List<PageOrderData> jsonlist;

	public String getTotalcount() {
		return totalcount;
	}

	public void setTotalcount(String totalcount) {
		this.totalcount = totalcount;
	}

	public String getTotalpage() {
		return totalpage;
	}

	public void setTotalpage(String totalpage) {
		this.totalpage = totalpage;
	}

	public String getCurrentpage() {
		return currentpage;
	}

	public void setCurrentpage(String currentpage) {
		this.currentpage = currentpage;
	}

	public String getPagecount() {
		return pagecount;
	}

	public void setPagecount(String pagecount) {
		this.pagecount = pagecount;
	}

	public List<PageOrderData> getJsonlist() {
		return jsonlist;
	}

	public void setJsonlist(List<PageOrderData> jsonlist) {
		this.jsonlist = jsonlist;
	}
	
}
