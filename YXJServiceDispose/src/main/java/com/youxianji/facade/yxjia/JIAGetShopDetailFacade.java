package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetShopDetailRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetShopDetailResponseBean;
import com.youxianji.service.IYxjBuserShopInfoService;

@Facade(command="8007",comment="JIA-获取的店铺详情")
@Scope("prototype")
public class JIAGetShopDetailFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserShopInfoService yxjBuserShopInfoService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetShopDetailRequestBean requestBean = (JIAGetShopDetailRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();

    	JIAGetShopDetailResponseBean responseBean = yxjBuserShopInfoService.getShopDetail(requestBean.getShopid());
    	responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
