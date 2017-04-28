package com.youxianji.service;

import java.math.BigDecimal;

import com.youxianji.facade.acountWithDraw.bean.ApplyWithDrawRequestBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;

public interface IYxjBuserWithdrawcashBandbankService {
	
	public YxjBuserWithdrawcashBandbank getBandbankInfo(String userid,String telephone);
	public YxjBuserWithdrawcashBandbank getBandbankHave(String userid);
	public YxjBuserWithdrawcashBandbank getBandbankNow(UserInfo byUserdb);
	
	public YxjBuserWithdrawcashBandbank getWithDrawBandbank(String userid,String telephone);
	
	public YxjBuserWithdrawcashBandbank getApplyWithDraw(String userid,String telephone,String paypass);
	
	public void saveBandBank(YxjBuserWithdrawcashBandbank bandbankInfo);
	
	public void updateBandBank(YxjBuserWithdrawcashBandbank bandbankInfo);
	
	public YxjBuserWithdrawcashBandbank getBandbankInfoById(String bandid);
	
	public void updateDoApplyWithDraw(ApplyWithDrawRequestBean requestBean,YxjBuserWithdrawcashBandbank applyWithDraw,UserInfo dbUserInfo,Double cashFact,BigDecimal bandPoundage);
}
