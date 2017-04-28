package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetNearByShopRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetNearByShopResponseBean;
import com.youxianji.service.IYxjBuserShopInfoService;

@Facade(command="8006",comment="JIA-获取周边店铺")
@Scope("prototype")
public class JIAGetNearByShopFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserShopInfoService yxjBuserShopInfoService;
	Logger logger = Logger.getLogger(JIAGetNearByShopFacade.class);
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetNearByShopRequestBean requestBean = (JIAGetNearByShopRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		logger.info("店铺经度:"+requestBean.getLongitude()+"纬度:"+requestBean.getLatitude());
    	JIAGetNearByShopResponseBean responseBean = yxjBuserShopInfoService.getNearByShopList(requestBean.getLongitude(), requestBean.getLatitude());
    	responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
