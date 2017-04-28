package com.youxianji.facade.bargain.bean;

import java.util.List;

import com.youxianji.facade.bargain.bean.json.AssistbargainBean;

public class GetAssistBargainUserInfoResponseBean {
	private String headimgurl;//发起人微信头像URL
	private String nickname;//发起人微信昵称
	private String leaveMoney;
	private String isassistbargain;//是否协助砍价
	private String ismakebargain;//是否发起过砍价
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

	public String getIsassistbargain() {
		return isassistbargain;
	}

	public void setIsassistbargain(String isassistbargain) {
		this.isassistbargain = isassistbargain;
	}

	public String getIsmakebargain() {
		return ismakebargain;
	}

	public void setIsmakebargain(String ismakebargain) {
		this.ismakebargain = ismakebargain;
	}

	public String getHeadimgurl() {
		return headimgurl;
	}

	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
}
