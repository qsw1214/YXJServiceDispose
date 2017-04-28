package com.youxianji.facade.product;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetNewHomeProdRequestBean;
import com.youxianji.facade.product.bean.GetNewHomeProdResponseBean;
import com.youxianji.service.IProdInfoService;

@Facade(command="3011",comment="获取首页商品列表(新)")
@Scope("prototype")
public class GetNewHomeProdFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetNewHomeProdRequestBean requestBean = (GetNewHomeProdRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		GetNewHomeProdResponseBean responseBean = prodInfoService.getNewHomeProdList(requestBean);
		responseParam.getParamdata().add(responseBean);
	
		return responseParam;
	}

}
