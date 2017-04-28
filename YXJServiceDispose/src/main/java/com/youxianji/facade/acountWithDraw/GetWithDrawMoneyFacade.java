package com.youxianji.facade.acountWithDraw;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.acountWithDraw.bean.GetWithDrawMoneyRequestBean;
import com.youxianji.facade.acountWithDraw.bean.GetWithDrawMoneyResponseBean;
import com.youxianji.pojo.UserBills;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.pojo.YxjBuserWithdrawcashSummation;
import com.youxianji.service.IUserBillsService;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.service.IYxjBuserWithdrawcashSummationService;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
@Facade(command="c001",comment="获取可提现金额接口")
@Scope("prototype")
public class GetWithDrawMoneyFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IUserBillsService userBillsService;
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IYxjBuserWithdrawcashSummationService yxjBuserWithdrawcashSummationService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetWithDrawMoneyResponseBean responseBean = new GetWithDrawMoneyResponseBean();
		GetWithDrawMoneyRequestBean requestBean  = (GetWithDrawMoneyRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		BigDecimal bandLimitamount = new BigDecimal(0);
		BigDecimal amountNow = new BigDecimal(0);
		Double operatemoney = 0.0;
		Double sumOut = 0.0;
		Double frozenAmount = 0.0;
		Double totalCash = 0.0;
		DecimalFormat dfs =new DecimalFormat("#.00");
		//获取到该用户的绑卡信息
		YxjBuserWithdrawcashBandbank dbBandbankInfo = yxjBuserWithdrawcashBandbankService.getBandbankHave(requestBean.getUserid());
		if(dbBandbankInfo != null){
			//最低提现金额
			bandLimitamount = dbBandbankInfo.getBandLimitamount();
		}
		//获取到该用户的总支出信息
		List<UserBills> billsOutList = userBillsService.getBillsByUserIdOut(requestBean.getUserid());
		if(billsOutList.size() != 0){
			//总支出金额
			for (UserBills userBills : billsOutList) {
				sumOut += userBills.getOperatemoney().doubleValue();
			}
			//amountOut = billsOutList.get(0).getAmountbalance();
		}
		//获取到该用户线下收款信息
		List<UserBills> billsInList = userBillsService.getBillsByUserIdIn(requestBean.getUserid());
		if(billsInList.size() != 0){
			//线下收款金额
			for (UserBills userBills : billsInList) {
				operatemoney += userBills.getOperatemoney().doubleValue();
			}
			//amountIn = billsInList.get(0).getAmountbalance();
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
		UserInfo dbUserInfo = userInfoService.getByUserId(requestBean.getUserid());
		if(dbUserInfo != null){
			//获取该用户的当前余额
			amountNow = dbUserInfo.getAmount();
		}
		
		//计算可提现金额(线下收款金额 - 总提现  - 冻结金额 )
		Double cashamount =  operatemoney - totalCash - frozenAmount;
		if(bandLimitamount != null){
			responseBean.setLimitcash(bandLimitamount.toString());
		}
		if(cashamount != null){
			if(cashamount < 0){
				responseBean.setCashamount("0");
			}else{
				responseBean.setCashamount(dfs.format(cashamount).toString());
			}
		}
		if(amountNow != null){
			responseBean.setAmountbalance(amountNow.toString());
		}
		responseParam.getParamdata().add(responseBean);
		return responseParam;
	}

}
