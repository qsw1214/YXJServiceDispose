package com.youxianji.facade.bargain.bean;

import java.util.List;

import com.youxianji.facade.bargain.bean.json.AssistbargainBean;


public class GetUserAssistBargainInfoResponseBean {
	private String leaveMoney;
	private List<AssistbargainBean> assistbargainlist;

	public String getLeaveMoney() {
		return leaveMoney;
	}

	public void setLeaveMoney(String leaveMoney) {
		this.leaveMoney = leaveMoney;
	}

	public List<AssistbargainBean> getAssistbargainlist() {
		return assistbargainlist;
	}

	public void setAssistbargainlist(List<AssistbargainBean> assistbargainlist) {
		this.assistbargainlist = assistbargainlist;
	}
	
	
	
}
