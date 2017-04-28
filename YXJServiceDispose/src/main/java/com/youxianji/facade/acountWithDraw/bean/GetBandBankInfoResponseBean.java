package com.youxianji.facade.acountWithDraw.bean;

public class GetBandBankInfoResponseBean {
	
	private String bandid;//绑定银行卡信息ID
	private String accountname;//银行账户名
	private String accountno;//银行卡号
	private String bankname;//银行名称
	private String bankcode;//银行编码
	private String idencard;//身份证号码
	
	
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
