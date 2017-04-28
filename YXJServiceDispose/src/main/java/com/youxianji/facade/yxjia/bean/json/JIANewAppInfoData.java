package com.youxianji.facade.yxjia.bean.json;

/**
 * 新版本信息数据
 * */
public class JIANewAppInfoData {

	private boolean hasnewversion = false;//是否有新版信息,false：没有,true：有
	private String newversionnum;//新版本编号
	private String newdownpath;//下载地址
	private String content;//更新内容
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public boolean isHasnewversion() {
		return hasnewversion;
	}
	public void setHasnewversion(boolean hasnewversion) {
		this.hasnewversion = hasnewversion;
	}
	public String getNewdownpath() {
		return newdownpath;
	}
	public void setNewdownpath(String newdownpath) {
		this.newdownpath = newdownpath;
	}
	public String getNewversionnum() {
		return newversionnum;
	}
	public void setNewversionnum(String newversionnum) {
		this.newversionnum = newversionnum;
	}
	
}
