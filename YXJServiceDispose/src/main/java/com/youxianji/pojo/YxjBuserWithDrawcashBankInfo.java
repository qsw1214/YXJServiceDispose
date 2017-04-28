package com.youxianji.pojo;

import java.util.Set;

public class YxjBuserWithDrawcashBankInfo {
	private String bankCode;
	private String bankName;
	
	//代付平台集合
	private Set<YxjBuserWithDrawcashPlatform> platforms;
	
	
	public Set<YxjBuserWithDrawcashPlatform> getPlatforms() {
		return platforms;
	}
	public void setPlatforms(Set<YxjBuserWithDrawcashPlatform> platforms) {
		this.platforms = platforms;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	
	
}
