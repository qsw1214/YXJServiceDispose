package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1008",comment="意见建议请求参数BEAN")
public class AdviceSuggestionRequestBean extends BaseRequest {
	
	private String userid;//用户ID
	private String content;//反馈内容
	private String contact;//联系方式
	private String Createtime;//创建时间
	private String telephone;//用户手机号码
	private String backtype;//反馈类型
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getCreatetime() {
		return Createtime;
	}
	public void setCreatetime(String createtime) {
		Createtime = createtime;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getBacktype() {
		return backtype;
	}
	public void setBacktype(String backtype) {
		this.backtype = backtype;
	}
	
	
}
