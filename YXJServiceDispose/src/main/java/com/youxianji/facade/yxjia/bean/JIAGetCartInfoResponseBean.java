package com.youxianji.facade.yxjia.bean;

import java.util.List;

import com.youxianji.facade.yxjia.bean.json.JIACartInfoBean;


public class JIAGetCartInfoResponseBean{
	
	private String cartquantity;//	String	购物车总数量
	private List<JIACartInfoBean> cartlist;
	
	private String totalprice;//	String	实际销售总价	
	private String membertotalprice;//会员总价 如果是新人或活动产品，就计算其新人价或活动价
	
	
	

	public String getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(String totalprice) {
		this.totalprice = totalprice;
	}

	public String getMembertotalprice() {
		return membertotalprice;
	}

	public void setMembertotalprice(String membertotalprice) {
		this.membertotalprice = membertotalprice;
	}

	public String getCartquantity() {
		return cartquantity;
	}

	public void setCartquantity(String cartquantity) {
		this.cartquantity = cartquantity;
	}

	public List<JIACartInfoBean> getCartlist() {
		return cartlist;
	}

	public void setCartlist(List<JIACartInfoBean> cartlist) {
		this.cartlist = cartlist;
	}

	
	
}
