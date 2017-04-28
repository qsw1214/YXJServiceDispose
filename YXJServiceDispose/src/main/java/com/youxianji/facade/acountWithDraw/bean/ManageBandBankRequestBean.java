package com.youxianji.facade.acountWithDraw.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="c004",comment="管理提现银行卡接口")
@Scope("prototype")
public class ManageBandBankRequestBean extends BaseRequest {

	private String userid;
	private String telephone;
	private String configflag;//标识 1添加 2修改
	private String bandid;//绑定信息ID
	private String accountname;//银行账户名
	private String accountno;//银行卡号
	private String bankname;//银行名称
	private String bankcode;//银行编码
	private String idencard;//身份证号码
	
	
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getConfigflag() {
		return configflag;
	}
	public void setConfigflag(String configflag) {
		this.configflag = configflag;
	}
	public String getBandid() {
		return bandid;
	}
	public void setBandid(String bandid) {
		this.bandid = bandid;
	}
	public String getAccountname() {
		return accountname;
	}
	public void setAccountname(String accountname) {
		this.accountname = accountname;
	}
	public String getAccountno() {
		return accountno;
	}
	public void setAccountno(String accountno) {
		this.accountno = accountno;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getIdencard() {
		return idencard;
	}
	public void setIdencard(String idencard) {
		this.idencard = idencard;
	}
	
	
}
