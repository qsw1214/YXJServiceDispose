package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsRequestBean;
import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsResponseBean;
import com.youxianji.service.IYxjBuserGatherInfoService;
import com.youxianji.web.util.CommonMobileResponse;

@Facade(command="6002",comment="JIA-线下收款订单查看")
@Scope("prototype")
public class JIAOfflineChargeBillsFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserGatherInfoService yxjBuserGatherInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAOfflineChargeBillsRequestBean requestBean = (JIAOfflineChargeBillsRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();

    	JIAOfflineChargeBillsResponseBean responseBean =  yxjBuserGatherInfoService.getOfflineChargeBills(requestBean);
    	responseParam.getParamdata().add(responseBean);
		return responseParam;
	}

}
