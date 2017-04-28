package com.youxianji.pojo;

import java.util.Date;

public class Groupbuy {
	
	private String id;//团购配置ID
	private Date begintime;//团购开始时间
	private Date endtime;//团购结束时间
	private String state;//状态 0.删除 1.正常
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}

}
