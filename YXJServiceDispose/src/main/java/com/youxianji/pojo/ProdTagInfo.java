
package com.youxianji.pojo;

import java.util.Date;

public class ProdTagInfo {

	private String tagId;
    private String tagName;
    private String tagurl;
    private String state;//标签状态 0.删除  1.正常
    private Date createTime;
	private String createUser;
	
	
	
	public String getTagurl() {
		return tagurl;
	}
	public void setTagurl(String tagurl) {
		this.tagurl = tagurl;
	}
	public String getTagId() {
		return tagId;
	}
	public void setTagId(String tagId) {
		this.tagId = tagId;
	}
	public String getTagName() {
		return tagName;
	}
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}	
	
	
}
