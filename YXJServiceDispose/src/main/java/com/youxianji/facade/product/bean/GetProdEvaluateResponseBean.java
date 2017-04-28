package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.ProdEvaluateBean;


public class GetProdEvaluateResponseBean{

	private String totalcount;//	Integer	总记录数
	private String totalpage;//	Integer	总页数
	private String currentpage;//	Integer	当前页
	private String pagecount;//	Integer	每页条数
	private List<ProdEvaluateBean> prodevaluatelist;

	
	
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

	public List<ProdEvaluateBean> getProdevaluatelist() {
		return prodevaluatelist;
	}

	public void setProdevaluatelist(List<ProdEvaluateBean> prodevaluatelist) {
		this.prodevaluatelist = prodevaluatelist;
	}
	
	
	
	
	
	
}
