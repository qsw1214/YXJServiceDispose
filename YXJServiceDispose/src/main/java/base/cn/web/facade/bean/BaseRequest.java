package base.cn.web.facade.bean;

import base.cn.annotation.InterfaceParam;

@InterfaceParam(comment="请求参数BEAN抽象类")
public abstract class BaseRequest {

	private PublicBean publicBean;
	private String userid;
	private String telephone;

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public PublicBean getPublicBean() {
		return publicBean;
	}

	public void setPublicBean(PublicBean publicBean) {
		this.publicBean = publicBean;
	}
}
