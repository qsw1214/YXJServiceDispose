package com.youxianji.pojo;

import java.util.Set;

public class YxjBuserWithDrawcashPlatform {
	
	private String platformId;//主键
	private String platformName;//代付平台名称
	private String platformCode;//代付平台编码
	private String platformUsernum;//用户编码
	private String platformStylef;//业务类型码
	private String platformVersion;//代付接口版本号
	private String platformCashpath;//代付文件存放路径
	private String platformBalancepath;//对账文件存放路径
	private String platformState;//状态  0删除 1正常
	private String platformRemark;//备注
	//银行集合
	private Set<YxjBuserWithDrawcashBankInfo> bankInfos;
	
	
	public Set<YxjBuserWithDrawcashBankInfo> getBankInfos() {
		return bankInfos;
	}
	public void setBankInfos(Set<YxjBuserWithDrawcashBankInfo> bankInfos) {
		this.bankInfos = bankInfos;
	}
	public String getPlatformId() {
		return platformId;
	}
	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}
	public String getPlatformName() {
		return platformName;
	}
	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}
	public String getPlatformCode() {
		return platformCode;
	}
	public void setPlatformCode(String platformCode) {
		this.platformCode = platformCode;
	}
	public String getPlatformUsernum() {
		return platformUsernum;
	}
	public void setPlatformUsernum(String platformUsernum) {
		this.platformUsernum = platformUsernum;
	}
	public String getPlatformStylef() {
		return platformStylef;
	}
	public void setPlatformStylef(String platformStylef) {
		this.platformStylef = platformStylef;
	}
	public String getPlatformVersion() {
		return platformVersion;
	}
	public void setPlatformVersion(String platformVersion) {
		this.platformVersion = platformVersion;
	}
	public String getPlatformCashpath() {
		return platformCashpath;
	}
	public void setPlatformCashpath(String platformCashpath) {
		this.platformCashpath = platformCashpath;
	}
	public String getPlatformBalancepath() {
		return platformBalancepath;
	}
	public void setPlatformBalancepath(String platformBalancepath) {
		this.platformBalancepath = platformBalancepath;
	}
	public String getPlatformState() {
		return platformState;
	}
	public void setPlatformState(String platformState) {
		this.platformState = platformState;
	}
	public String getPlatformRemark() {
		return platformRemark;
	}
	public void setPlatformRemark(String platformRemark) {
		this.platformRemark = platformRemark;
	}
	
	
}
