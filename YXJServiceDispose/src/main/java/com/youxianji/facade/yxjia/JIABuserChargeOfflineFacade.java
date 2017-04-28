package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIABuserChargeOfflineRequestBean;
import com.youxianji.service.IYxjBuserGatherInfoService;
import com.youxianji.service.IYxjBuserShopInfoService;

@Facade(command="8005",comment="JIA-商家线下收款")
@Scope("prototype")
public class JIABuserChargeOfflineFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserShopInfoService yxjBuserShopInfoService;
	@Resource
	private IYxjBuserGatherInfoService yxjBuserGatherInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIABuserChargeOfflineRequestBean requestBean = (JIABuserChargeOfflineRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        yxjBuserGatherInfoService.doChargeByOffline(requestBean);
        	
		return responseParam;
	}

}
