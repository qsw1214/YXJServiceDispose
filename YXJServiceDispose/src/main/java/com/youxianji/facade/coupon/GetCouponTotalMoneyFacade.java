package com.youxianji.facade.coupon;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.coupon.bean.GetCouponTotalMoneyRequestBean;
import com.youxianji.facade.coupon.bean.GetCouponTotalMoneyResponseBean;
import com.youxianji.pojo.YxjUserCouponGrantConfig;
import com.youxianji.service.IYxjUserCouponGrantConfigService;
import com.youxianji.web.util.ErrorEnum;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

@Facade(command="h004",comment="获取指定红包发放类型总金额")
@Scope("prototype")
public class GetCouponTotalMoneyFacade extends AbsFacade{
	
	@Resource
	private IYxjUserCouponGrantConfigService yxjUserCouponGrantConfigService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetCouponTotalMoneyRequestBean requestBean = (GetCouponTotalMoneyRequestBean)baseRequest;
		GetCouponTotalMoneyResponseBean responseBean = new GetCouponTotalMoneyResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String configFlag = requestBean.getConfigflag();
		YxjUserCouponGrantConfig grantConfig = yxjUserCouponGrantConfigService.findGrantConfig(configFlag);
		
		if(ObjectTools.isNullByObject(grantConfig)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("活动已经结束!");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}else{
			responseBean.setTotalmoney(String.valueOf(grantConfig.getTotalMoney()));
			responseParam.getParamdata().add(responseBean);
		}
		
		return responseParam;
	}
	
	
}
