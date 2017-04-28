package com.youxianji.pojo;

import java.util.Date;


public class Afternoontea {

	 public String id;
	 public String telephone;
	 public String companyname;
	 public int employquantity;
	 public String contractpeople;
	 public String position;
	 public String invitecode;
	 public String remark;
	 private Date createTime;
	 private Date appointment;//预约时间

	 
	 
	 
	 
	 
	 
	public Date getAppointment() {
		return appointment;
	}
	public void setAppointment(Date appointment) {
		this.appointment = appointment;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getCompanyname() {
		return companyname;
	}
	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}
	public int getEmployquantity() {
		return employquantity;
	}
	public void setEmployquantity(int employquantity) {
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
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	 
	 
	 

}
