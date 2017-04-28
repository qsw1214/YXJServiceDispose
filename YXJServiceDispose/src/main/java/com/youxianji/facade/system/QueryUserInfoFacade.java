package com.youxianji.facade.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.QueryUserInfoRequestBean;
import com.youxianji.facade.system.bean.QueryUserInfoResponseBean;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.service.IUserInfoService;

@Facade(command="1006",comment="获取用户信息业务处理")
@Scope("prototype")
public class QueryUserInfoFacade extends AbsFacade{
	
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ICouponUseInfoService couponUseInfoService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		QueryUserInfoRequestBean requestBean = (QueryUserInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		QueryUserInfoResponseBean responseBean = new QueryUserInfoResponseBean();
		UserInfo user = userInfoService.getByUserId(requestBean.getUserid());
		
		List<CouponUseInfo> couponList = couponUseInfoService.getUsefulCouponList(requestBean.getUserid());
		if(couponList != null){
			responseBean.setCouponcount(String.valueOf(couponList.size()));
		}else{
			responseBean.setCouponcount("0");
		}
		
		responseBean.setAmount(user.getAmount().toString());
		responseBean.setUserId(user.getUserId());
		
		responseParam.getParamdata().add(responseBean);

		return responseParam;
		
	}

}
