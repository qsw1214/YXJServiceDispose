package com.youxianji.facade.product;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProdEvaluateRequestBean;
import com.youxianji.service.IProdEvaluateService;

@Facade(command="3013",comment="获取商品评价列表")
@Scope("prototype")
public class GetProdEvaluateFacade extends AbsFacade{
	
	@Resource
	private IProdEvaluateService prodEvaluateService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProdEvaluateRequestBean requestBean = (GetProdEvaluateRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		responseParam.getParamdata().add(
				prodEvaluateService.getProdEvaludate(requestBean));
	
		return responseParam;
		
		
		
		
	}

}
