package com.youxianji.dao;

import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;

public interface IYxjBuserWithdrawcashBandbankDAO {
	
	public YxjBuserWithdrawcashBandbank getBandbankInfo(String userid,String telephone);
	
	public YxjBuserWithdrawcashBandbank getWithDrawBandbank(String userid,String telephone);
	
	public YxjBuserWithdrawcashBandbank getApplyWithDraw(String userid,String telephone,String paypass);
	
	public void saveBandBank(YxjBuserWithdrawcashBandbank bandbankInfo);
	
	public void updateBandBank(YxjBuserWithdrawcashBandbank bandbankInfo);
	
	public YxjBuserWithdrawcashBandbank getBandbankInfoById(String bandid);
	
	public YxjBuserWithdrawcashBandbank getBandbankHave(String userid);
}
