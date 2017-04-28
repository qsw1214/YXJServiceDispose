package com.youxianji.facade.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProductByTagRequestBean;
import com.youxianji.facade.product.bean.GetProductByTagResponseBean;
import com.youxianji.pojo.FreshmanProduct;
import com.youxianji.service.IGetProductByTagService;
@Facade(command="3008",comment="按标签获取商品接口")
@Scope("prototype")
public class GetProductByTagFacade extends AbsFacade {
	
	@Resource
	private IGetProductByTagService getProductByTagService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProductByTagRequestBean requestBean = (GetProductByTagRequestBean)baseRequest;
		GetProductByTagResponseBean responseBean = new GetProductByTagResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<FreshmanProduct> ProductByTagList = getProductByTagService.findProductByTag(requestBean.getUserid(), requestBean.getTelephone(), requestBean.getTagid());
		for(int i=0 ; i<ProductByTagList.size(); i++){   
			  FreshmanProduct freshmanProduct = ProductByTagList.get(i);   
			  responseBean.setFreshmanProduct(freshmanProduct);
		   }   
		responseParam.getParamdata().add(responseBean);

		return responseParam;
	}

}





















