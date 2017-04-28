package com.youxianji.facade.acountWithDraw;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.acountWithDraw.bean.ApplyWithDrawRequestBean;
import com.youxianji.facade.acountWithDraw.bean.ApplyWithDrawResponseBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.pojo.YxjBuserWithdrawcashNationalholiday;
import com.youxianji.pojo.YxjBuserWithdrawcashUpper;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IYxjBuserNationalholidayService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.service.IYxjBuserWithdrawcashTraceService;
import com.youxianji.service.IYxjBuserWithdrawcashUpperService;
import com.youxianji.web.util.ErrorEnum;
@Facade(command="c006",comment="申请提现接口")
@Scope("prototype")
public class ApplyWithDrawFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IYxjBuserNationalholidayService yxjBuserNationalholidayService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IYxjBuserWithdrawcashUpperService yxjBuserWithdrawcashUpperService;
	@Resource
	private IYxjBuserWithdrawcashTraceService yxjBuserWithdrawcashTraceService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ApplyWithDrawResponseBean responseBean = new ApplyWithDrawResponseBean();
		ApplyWithDrawRequestBean requestBean  = (ApplyWithDrawRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		UserInfo dbUserInfo = userInfoService.getByUserId(requestBean.getUserid());
		YxjBuserWithdrawcashBandbank applyWithDraw = yxjBuserWithdrawcashBandbankService.getApplyWithDraw(requestBean.getUserid(), requestBean.getTelephone(), requestBean.getPaypass());
		if(!isNowDay(requestBean)){
			//如果当日已经有提现记录
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您今天已经提过现，每天只能提现一次");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		if(applyWithDraw != null){
			//用户存在，支付密码正确
			BigDecimal bandLimitamount = applyWithDraw.getBandLimitamount();//提现最低限额
			Double cashamoun = Double.valueOf(requestBean.getCashamount());//申请提现金额
			BigDecimal bandPoundage = applyWithDraw.getBandPoundage();//提现手续费
			
			if(cashamoun < bandLimitamount.doubleValue()){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("申请提现金额不能小于提现最低限额");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			Double cashFact = cashamoun - bandPoundage.doubleValue();//实际到账金额
		//验证是否在法定节假日之内
		if(isHolyday()){
			//如果不是节假日
			if(isWorkDay()){
				//如果是调休日
				yxjBuserWithdrawcashBandbankService.updateDoApplyWithDraw(requestBean, applyWithDraw, dbUserInfo, cashFact, bandPoundage);
			}else{
				if(isWeekend()){
					//如果不是周六日
					try {
						yxjBuserWithdrawcashBandbankService.updateDoApplyWithDraw(requestBean, applyWithDraw, dbUserInfo, cashFact, bandPoundage);
					} catch (Exception e) {
						ErrorEnum.valueOf("FAIL_5555").changetMessage("提现失败");
						throw new BaseException(ErrorEnum.FAIL_5555);
					}
				}else{
					//在周六日
					ErrorEnum.valueOf("FAIL_5555").changetMessage("周六日不能提现");
					throw new BaseException(ErrorEnum.FAIL_5555);
				}
			}
		}else{
			//在法定节假日之内
			ErrorEnum.valueOf("FAIL_5555").changetMessage("法定节假日不能提现");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		}else{
			ErrorEnum.valueOf("FAIL_5555").changetMessage("支付密码错误或用户不存在");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		return responseParam;
	}
	//判断是否在节假日之内
	public boolean isHolyday(){
		YxjBuserWithdrawcashNationalholiday nationalholidayByTime = yxjBuserNationalholidayService.getNationalholidayByTime(new Date());
		if(nationalholidayByTime != null){
			return false;
		}
		return true;
	}
	//判断是否在周六日
	public boolean isWeekend(){
		SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
		String week = sdf.format(new Date());
		if(week.equals("星期六") || week.equals("星期日")){
			return false;
		}
		return true;
	}
	//判断是否是调休日
	private boolean isWorkDay(){
		Date date = new Date();
		String time1 = String.format("%tF",date)+" 00:00:00";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date parse1 = sdf.parse(time1);
			YxjBuserWithdrawcashNationalholiday workholidayByTime = yxjBuserNationalholidayService.getNationalholidayByWorkTime(parse1);
			if(workholidayByTime != null){
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	//判断是否是当日
	public boolean isNowDay(ApplyWithDrawRequestBean requestBean){
		Date date = new Date();
		String time1 = String.format("%tF",date)+" 00:00:00";
		String time2 = String.format("%tF",date)+" 23:59:59";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			Date parse1 = sdf.parse(time1);
			Date parse2 = sdf.parse(time2);
			List<YxjBuserWithdrawcashUpper> withdrawcashUpperList = yxjBuserWithdrawcashUpperService.getWithdrawcashUpper(requestBean.getUserid(), parse1,parse2);
			if(withdrawcashUpperList.size() != 0){
				return false;
			}else{
				return true;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}
	
}
