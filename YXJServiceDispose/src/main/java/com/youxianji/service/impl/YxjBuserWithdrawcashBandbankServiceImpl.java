package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.youxianji.dao.IYxjBuserWithdrawcashBandbankDAO;
import com.youxianji.facade.acountWithDraw.bean.ApplyWithDrawRequestBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.pojo.YxjBuserWithdrawcashDetail;
import com.youxianji.pojo.YxjBuserWithdrawcashSummation;
import com.youxianji.pojo.YxjBuserWithdrawcashTrace;
import com.youxianji.pojo.YxjBuserWithdrawcashUpper;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.service.IYxjBuserWithdrawcashDetailService;
import com.youxianji.service.IYxjBuserWithdrawcashSummationService;
import com.youxianji.service.IYxjBuserWithdrawcashTraceService;
import com.youxianji.service.IYxjBuserWithdrawcashUpperService;
import com.youxianji.util.bill.BillTransact;
import com.youxianji.util.bill.parambean.CUserParam;

import base.cn.util.UUIDGenerator;
@Service("yxjBuserWithdrawcashBandbankService")
public class YxjBuserWithdrawcashBandbankServiceImpl implements IYxjBuserWithdrawcashBandbankService {
	@Resource
	private IYxjBuserWithdrawcashBandbankDAO yxjBuserWithdrawcashBandbankDAO;
	@Resource
	private IYxjBuserWithdrawcashUpperService yxjBuserWithdrawcashUpperService;
	@Resource
	private IYxjBuserWithdrawcashTraceService yxjBuserWithdrawcashTraceService;
	@Resource
	private IYxjBuserWithdrawcashSummationService yxjBuserWithdrawcashSummationService;
	@Resource
	private IYxjBuserWithdrawcashDetailService yxjBuserWithdrawcashDetailService;
	
	@Override
	public YxjBuserWithdrawcashBandbank getBandbankInfo(String userid, String telephone) {
		
		return yxjBuserWithdrawcashBandbankDAO.getBandbankInfo(userid, telephone);
	}

	@Override
	public YxjBuserWithdrawcashBandbank getWithDrawBandbank(String userid, String telephone) {
		return yxjBuserWithdrawcashBandbankDAO.getWithDrawBandbank(userid, telephone);
	}

	@Override
	public YxjBuserWithdrawcashBandbank getApplyWithDraw(String userid, String telephone, String paypass) {
		return yxjBuserWithdrawcashBandbankDAO.getApplyWithDraw(userid, telephone, paypass);
	}

	@Override
	public void saveBandBank(YxjBuserWithdrawcashBandbank bandbankInfo) {
		yxjBuserWithdrawcashBandbankDAO.saveBandBank(bandbankInfo);
	}

	@Override
	public void updateBandBank(YxjBuserWithdrawcashBandbank bandbankInfo) {
		yxjBuserWithdrawcashBandbankDAO.updateBandBank(bandbankInfo);
	}

	@Override
	public YxjBuserWithdrawcashBandbank getBandbankInfoById(String bandid) {
		return yxjBuserWithdrawcashBandbankDAO.getBandbankInfoById(bandid);
	}
	@Transactional
	public void updateDoApplyWithDraw(ApplyWithDrawRequestBean requestBean,YxjBuserWithdrawcashBandbank applyWithDraw,UserInfo dbUserInfo,Double cashFact,BigDecimal bandPoundage){
		
		BigDecimal bd = new BigDecimal(requestBean.getCashamount());//申請提現額度
		//保存提现记录
		YxjBuserWithdrawcashUpper drawcashUpper = new YxjBuserWithdrawcashUpper();
		drawcashUpper.setAccountName(applyWithDraw.getBandAccountname());
		drawcashUpper.setCashApply(bd);
		drawcashUpper.setCashApplyer(dbUserInfo);
		drawcashUpper.setCashApplytime(new Date());
		drawcashUpper.setCashFact(new BigDecimal(cashFact));
		drawcashUpper.setCashPoundage(bandPoundage);
		drawcashUpper.setCashState("1");
		drawcashUpper.setCashTelephone(requestBean.getTelephone());
		drawcashUpper.setDangerFlag("0");
		drawcashUpper.setReturnFlag("0");
		drawcashUpper.setCashId(UUIDGenerator.getUUID());
		yxjBuserWithdrawcashUpperService.insertWithdrawcashUpper(drawcashUpper);
		
		//保存提现明细记录
		YxjBuserWithdrawcashDetail drawcashDetail = new YxjBuserWithdrawcashDetail();
		drawcashDetail.setCashApply(bd);
		drawcashDetail.setCashdetailApplydate(new Date());
		drawcashDetail.setCashdetailId(UUIDGenerator.getUUID());
		drawcashDetail.setCashdetailState("1");
		drawcashDetail.setCashdetailTelephone(requestBean.getTelephone());
		drawcashDetail.setCashdetailUserid(requestBean.getUserid());
		drawcashDetail.setCashdetailUsername(dbUserInfo.getUserName());
		drawcashDetail.setCashFact(new BigDecimal(cashFact));
		drawcashDetail.setCashPoundage(bandPoundage);
		drawcashDetail.setCashUpper(drawcashUpper);
		drawcashDetail.setDangerFlag("0");
		drawcashDetail.setReturnFlag("0");
		yxjBuserWithdrawcashDetailService.saveWithdrawcashDetail(drawcashDetail);
		
		//保存提现轨迹
		YxjBuserWithdrawcashTrace drawcashTrace = new YxjBuserWithdrawcashTrace();
		drawcashTrace.setCashState("1");
		drawcashTrace.setCashUpper(drawcashUpper);
		drawcashTrace.setOperateTime(new Date());
		drawcashTrace.setTraceId(UUIDGenerator.getUUID());
		//drawcashTrace.setOperator("");
		drawcashTrace.setRemark("手机用户提现");
		yxjBuserWithdrawcashTraceService.insertWithdrawcashTrace(drawcashTrace);
		
		//执行提现操作，扣减用户的账户额度
		CUserParam cuserParam = new CUserParam();
		cuserParam.setUserId(requestBean.getUserid());
		cuserParam.setOperatesn(drawcashUpper.getCashId());
		cuserParam.setOperatemoney(bd);
		cuserParam.setBillsflag("1");
		cuserParam.setOperatedesc("额度提现");
		BillTransact.mobileUserTransact(cuserParam);
		
		YxjBuserWithdrawcashSummation summation = yxjBuserWithdrawcashSummationService.getSummation(requestBean.getUserid());
		if(summation == null){
			//添加冻结金额
			YxjBuserWithdrawcashSummation drawcashSummation = new YxjBuserWithdrawcashSummation();
			drawcashSummation.setFrozenAmount(bd);
			drawcashSummation.setSummationUserid(requestBean.getUserid());
			drawcashSummation.setTotalCash(new BigDecimal(0));
			drawcashSummation.setVersion(1);
			yxjBuserWithdrawcashSummationService.insertWithdrawcashSummation(drawcashSummation);
		}
		//修改累计冻结金额
		//Integer version = summation.getVersion();//乐观锁版本号
		if(summation != null){
			BigDecimal frozenAmount = summation.getFrozenAmount();
			BigDecimal sumCash = frozenAmount.add(bd);//累计冻结金额
			summation.setFrozenAmount(sumCash);
			yxjBuserWithdrawcashSummationService.updateWithdrawcashSummation(summation);
		}
	}

	@Override
	public YxjBuserWithdrawcashBandbank getBandbankHave(String userid) {
		return yxjBuserWithdrawcashBandbankDAO.getBandbankHave(userid);
	}

	@Override
	public YxjBuserWithdrawcashBandbank getBandbankNow(UserInfo byUserdb) {
		// TODO Auto-generated method stub
		return null;
	}
}
