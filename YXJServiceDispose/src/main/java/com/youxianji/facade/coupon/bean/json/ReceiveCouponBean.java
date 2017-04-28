package com.youxianji.facade.coupon.bean.json;

public class ReceiveCouponBean {

	private String userid;//	用户ID
	private String headimgurl;//微信头像URL
	private String nickname;//微信昵称
	private String receivetime;//领取红包时间
	private String receivemoney;//领取红包金额
	private String bestlucky;//手气最好 0.不是  1.是
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	public String getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(String receivetime) {
		this.receivetime = receivetime;
	}
	public String getReceivemoney() {
		return receivemoney;
	}
	public void setReceivemoney(String receivemoney) {
		this.receivemoney = receivemoney;
	}
	public String getBestlucky() {
		return bestlucky;
	}
	public void setBestlucky(String bestlucky) {
		this.bestlucky = bestlucky;
	}
	
	
}
