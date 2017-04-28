package com.youxianji.facade.acountWithDraw;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.acountWithDraw.bean.GetPoundAgeRequestBean;
import com.youxianji.facade.acountWithDraw.bean.GetPoundAgeResponseBean;
import com.youxianji.pojo.UserBills;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.pojo.YxjBuserWithdrawcashSummation;
import com.youxianji.service.IUserBillsService;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.service.IYxjBuserWithdrawcashSummationService;
import com.youxianji.web.util.CommonMobileResponse;
import com.youxianji.web.util.ErrorEnum;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
@Facade(command="c005",comment="获取手续费接口")
@Scope("prototype")
public class GetPoundAgeFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IUserBillsService userBillsService;
	@Resource
	private IYxjBuserWithdrawcashSummationService yxjBuserWithdrawcashSummationService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetPoundAgeResponseBean responseBean = new GetPoundAgeResponseBean();
		GetPoundAgeRequestBean requestBean  = (GetPoundAgeRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		Double operatemoney = 0.0;
		Double totalCash = 0.0;
		Double frozenAmount = 0.0;
		//获取到该用户线下收款信息
		List<UserBills> billsInList = userBillsService.getBillsByUserIdIn(requestBean.getUserid());
		if(billsInList.size() != 0){
			//线下收款金额
			for (UserBills userBills : billsInList) {
				operatemoney += userBills.getOperatemoney().doubleValue();
			}
		}
		YxjBuserWithdrawcashSummation summation = yxjBuserWithdrawcashSummationService.getSummation(requestBean.getUserid());
		if(summation != null){
			//累计冻结金额
			if(summation.getFrozenAmount() != null){
				frozenAmount = summation.getFrozenAmount().doubleValue();
			}
			//累计提现金额
			if(summation.getTotalCash() != null){
				totalCash = summation.getTotalCash().doubleValue();
			}
		}
		//计算可提现金额(线下收款金额 - 总提现  - 冻结金额 )
		Double cashamount =  operatemoney - totalCash - frozenAmount;
		if(cashamount <= 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您没有可提现金额");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		YxjBuserWithdrawcashBandbank dbWithDrawBandbank = yxjBuserWithdrawcashBandbankService.getWithDrawBandbank(requestBean.getUserid(), requestBean.getTelephone());
		UserInfo dbUserInfo = userInfoService.getByUserId(requestBean.getUserid());
		Double cashamoun = Double.valueOf(requestBean.getCashamount());//申请提现金额
		if(dbUserInfo != null){
			BigDecimal amount = dbUserInfo.getAmount();
			if(amount != null){
				if(cashamoun > amount.doubleValue()){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("申请提现金额不能大于当前余额");
					throw new BaseException(ErrorEnum.FAIL_5555);
				}
				if(cashamoun > cashamount){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("申请提现金额不能大于可提现金额");
					throw new BaseException(ErrorEnum.FAIL_5555);
				}
			}
		}
		if(dbWithDrawBandbank != null){
			BigDecimal bandLimitamount = dbWithDrawBandbank.getBandLimitamount();//提现最低限额
			
			if(bandLimitamount.doubleValue() <= cashamoun){
				//如果申请提现金额大于最低限额
				Double  factamount = cashamoun - dbWithDrawBandbank.getBandPoundage().doubleValue();//实际到账金额
				responseBean.setFactamount(String.valueOf(factamount));
				responseBean.setPoundage(String.valueOf(dbWithDrawBandbank.getBandPoundage()));
			}else{
				ErrorEnum.valueOf("FAIL_5555").changetMessage("申请提现金额不能小于提现最低限额");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			responseParam.getParamdata().add(responseBean);
		}else{
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该用户暂未通过提现审核");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		return responseParam;
	}

}
