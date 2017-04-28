package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="1015",comment="下午茶申请接口")
public class ApplyAfternoonTeaRequestBean extends BaseRequest{

	 public String companyname;
	 public String employquantity;
	 public String contractpeople;
	 public String position;

	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public String getEmployquantity() {
		return employquantity;
	}
	public void setEmployquantity(String employquantity) {
		this.employquantity = employquantity;
	}
	public String getContractpeople() {
		return contractpeople;
	}
	public void setContractpeople(String contractpeople) {
		this.contractpeople = contractpeople;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	 
	 
	 
	 
	 
	
}
