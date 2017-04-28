package com.youxianji.facade.yxjia.bean;

import java.util.List;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

import com.youxianji.facade.yxjia.bean.json.JIACartDetailBean;

@InterfaceParam(command="8003",comment="JIA-结算购物车BEAN")
public class JIASettleCartInfoRequestBean extends BaseRequest{
	private List<JIACartDetailBean> detaillist;

	public List<JIACartDetailBean> getDetaillist() {
		return detaillist;
	}

	public void setDetaillist(List<JIACartDetailBean> detaillist) {
		this.detaillist = detaillist;
	}


	
	
	
	
	
}
