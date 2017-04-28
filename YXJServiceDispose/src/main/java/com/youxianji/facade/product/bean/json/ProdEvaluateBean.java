package com.youxianji.facade.product.bean.json;

public class ProdEvaluateBean {
    
	private String telephone;//	String	手机号	
	private String starlevel;//	String	星级	
	private String createtime;//	String	评价时间	
	private String prodevaluate;//	String 评价内容
	private String evaluatereplay;//回复
	
	
	public String getEvaluatereplay() {
		return evaluatereplay;
	}
	public void setEvaluatereplay(String evaluatereplay) {
		this.evaluatereplay = evaluatereplay;
	}
	public String getProdevaluate() {
		return prodevaluate;
	}
	public void setProdevaluate(String prodevaluate) {
		this.prodevaluate = prodevaluate;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getStarlevel() {
		return starlevel;
	}
	public void setStarlevel(String starlevel) {
		this.starlevel = starlevel;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	
	
    

}
