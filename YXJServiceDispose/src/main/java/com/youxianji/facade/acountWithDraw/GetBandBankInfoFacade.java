package com.youxianji.facade.acountWithDraw;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.acountWithDraw.bean.GetBandBankInfoRequestBean;
import com.youxianji.facade.acountWithDraw.bean.GetBandBankInfoResponseBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.web.util.ErrorEnum;
@Facade(command="c002",comment="获取绑定银行卡信息接口")
@Scope("prototype")
public class GetBandBankInfoFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IUserInfoService userInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetBandBankInfoResponseBean responseBean = new GetBandBankInfoResponseBean();
		GetBandBankInfoRequestBean requestBean  = (GetBandBankInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		YxjBuserWithdrawcashBandbank bandbankInfo = yxjBuserWithdrawcashBandbankService.getBandbankHave(requestBean.getUserid());
		UserInfo byUserdb = userInfoService.getByUserId(requestBean.getUserid());
		
		if(bandbankInfo != null){
			responseBean.setAccountname(bandbankInfo.getBandAccountname());
			responseBean.setAccountno(bandbankInfo.getBandCardno());
			responseBean.setBandid(bandbankInfo.getBandid());
			if(bandbankInfo.getBankInfo() != null){
				responseBean.setBankcode(bandbankInfo.getBankInfo().getBankCode());
				responseBean.setBankname(bandbankInfo.getBankInfo().getBankName());
			}
			responseBean.setIdencard(bandbankInfo.getBandIdencard());
			responseParam.getParamdata().add(responseBean);
		}else{
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该用户未绑定银行卡");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		return responseParam;
	}

}
