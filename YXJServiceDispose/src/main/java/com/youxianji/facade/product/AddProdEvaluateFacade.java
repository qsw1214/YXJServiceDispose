package com.youxianji.facade.product;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.AddProdEvaluateRequestBean;
import com.youxianji.service.IProdEvaluateService;

@Facade(command="3012",comment="添加商品评价")
@Scope("prototype")
public class AddProdEvaluateFacade extends AbsFacade{
	
	@Resource
	private IProdEvaluateService prodEvaluateService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		AddProdEvaluateRequestBean requestBean = (AddProdEvaluateRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
            
		prodEvaluateService.addProdEvaluate(requestBean);
			

		return responseParam;
		
		
		
		
	}

}
