package com.youxianji.facade.trade.bean;

import java.util.List;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

import com.youxianji.facade.trade.bean.json.CartDetailBean;

@InterfaceParam(command="4010",comment="结算购物车BEAN")
public class SettleCartInfoRequestBean extends BaseRequest{
	private List<CartDetailBean> detaillist;
	private String invitecode;

	
	public String getInvitecode() {
		return invitecode;
	}

	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}

	public List<CartDetailBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<CartDetailBean> detaillist) {
		this.detaillist = detaillist;
	}
	
	
	
	
}
