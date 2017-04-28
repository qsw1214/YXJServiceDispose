package com.youxianji.facade.acountWithDraw;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.acountWithDraw.bean.GetWithdrawcashBandbankRequestBean;
import com.youxianji.facade.acountWithDraw.bean.GetWithdrawcashBandbankResponseBean;
import com.youxianji.facade.acountWithDraw.bean.jsonBean.WithdrawcashBandbankBean;
import com.youxianji.pojo.YxjBuserWithDrawcashBankInfo;
import com.youxianji.service.IYxjBuserWithDrawcashBankInfoService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
@Facade(command="c003",comment="获取可绑定银行信息接口")
@Scope("prototype")
public class GetWithDrawBandBankFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IYxjBuserWithDrawcashBankInfoService yxjBuserWithDrawcashBankInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetWithdrawcashBandbankResponseBean responseBean = new GetWithdrawcashBandbankResponseBean();
		GetWithdrawcashBandbankRequestBean requestBean  = (GetWithdrawcashBandbankRequestBean)baseRequest;
		
		BaseResponse responseParam = new BaseResponse();
		List<YxjBuserWithDrawcashBankInfo> bankInfoAllList = yxjBuserWithDrawcashBankInfoService.getBankInfoAll();
		List<WithdrawcashBandbankBean> banklist  = new ArrayList<WithdrawcashBandbankBean>();
		if(bankInfoAllList.size() != 0){
			for (YxjBuserWithDrawcashBankInfo yxjBuserWithDrawcashBankInfo : bankInfoAllList) {
				WithdrawcashBandbankBean bandbankBean = new WithdrawcashBandbankBean();
				bandbankBean.setBankcode(yxjBuserWithDrawcashBankInfo.getBankCode());
				bandbankBean.setBankname(yxjBuserWithDrawcashBankInfo.getBankName());
				banklist.add(bandbankBean);
			}
			responseBean.setBanklist(banklist);
			responseParam.getParamdata().add(responseBean);
		}
		//YxjBuserWithdrawcashBandbank bandbankInfo = yxjBuserWithdrawcashBandbankService.getWithDrawBandbank(requestBean.getUserid(), requestBean.getTelephone());
			
		return responseParam;
	}

}
