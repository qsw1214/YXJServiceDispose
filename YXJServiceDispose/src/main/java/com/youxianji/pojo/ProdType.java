package com.youxianji.pojo;

public class ProdType {
	
	private String typeid;//'类别ID',
	private String typename;//'类别名称',
	private Integer depth;//'类别级别',
	private String parentid;//'上级类别',
	private String buserid;//'所属商户ID'
	private String state;//1 正常 0删除
	private Integer serialnumber;//排序号
	
	
	public Integer getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getBuserid() {
		return buserid;
	}
	public void setBuserid(String buserid) {
		this.buserid = buserid;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	public Integer getDepth() {
		return depth;
	}
	public void setDepth(Integer depth) {
		this.depth = depth;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	
	
	
	
	
}
