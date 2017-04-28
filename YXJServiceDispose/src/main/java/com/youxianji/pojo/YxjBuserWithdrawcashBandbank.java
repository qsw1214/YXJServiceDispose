package com.youxianji.pojo;

import java.math.BigDecimal;

public class YxjBuserWithdrawcashBandbank {

	private static final long serialVersionUID = -6138197532130544368L;
	private String bandid;
	private UserInfo userinfo;//用户id
	private String bandBankprovince;
	private String bandBankcity;
	private String bandBankname;
	private YxjBuserWithDrawcashBankInfo bankInfo;
	private String bandSubbank;
	private String bandUnitcode;
	private String bandCardno;
	private String bandAccountname;
	private String bandIdencard;
	private BigDecimal bandPoundage;//'提现手续费',
	private BigDecimal bandLimitamount;//'提现最低限额',
	private String enableFlag;//0 不能提现 1可以提现
	
	
	public YxjBuserWithDrawcashBankInfo getBankInfo() {
		return bankInfo;
	}
	public void setBankInfo(YxjBuserWithDrawcashBankInfo bankInfo) {
		this.bankInfo = bankInfo;
	}
	
	
	public String getBandid() {
		return bandid;
	}
	public void setBandid(String bandid) {
		this.bandid = bandid;
	}
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public String getBandBankprovince() {
		return bandBankprovince;
	}
	public void setBandBankprovince(String bandBankprovince) {
		this.bandBankprovince = bandBankprovince;
	}
	public String getBandBankcity() {
		return bandBankcity;
	}
	public void setBandBankcity(String bandBankcity) {
		this.bandBankcity = bandBankcity;
	}
	public String getBandBankname() {
		return bandBankname;
	}
	public void setBandBankname(String bandBankname) {
		this.bandBankname = bandBankname;
	}
	public String getBandSubbank() {
		return bandSubbank;
	}
	public void setBandSubbank(String bandSubbank) {
		this.bandSubbank = bandSubbank;
	}
	public String getBandUnitcode() {
		return bandUnitcode;
	}
	public void setBandUnitcode(String bandUnitcode) {
		this.bandUnitcode = bandUnitcode;
	}
	public String getBandCardno() {
		return bandCardno;
	}
	public void setBandCardno(String bandCardno) {
		this.bandCardno = bandCardno;
	}
	public String getBandAccountname() {
		return bandAccountname;
	}
	public void setBandAccountname(String bandAccountname) {
		this.bandAccountname = bandAccountname;
	}
	public String getBandIdencard() {
		return bandIdencard;
	}
	public void setBandIdencard(String bandIdencard) {
		this.bandIdencard = bandIdencard;
	}
	public BigDecimal getBandPoundage() {
		return bandPoundage;
	}
	public void setBandPoundage(BigDecimal bandPoundage) {
		this.bandPoundage = bandPoundage;
	}
	public BigDecimal getBandLimitamount() {
		return bandLimitamount;
	}
	public void setBandLimitamount(BigDecimal bandLimitamount) {
		this.bandLimitamount = bandLimitamount;
	}
	public String getEnableFlag() {
		return enableFlag;
	}
	public void setEnableFlag(String enableFlag) {
		this.enableFlag = enableFlag;
	}
	
}
