package com.youxianji.facade.product.bean.json;

public class ProdTypeBean {
    private String typeid;//	String	类别ID
    private String typelevel;
	private String typename;//	String	类别名称

	
	public String getTypelevel() {
		return typelevel;
	}
	public void setTypelevel(String typelevel) {
		this.typelevel = typelevel;
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


}
