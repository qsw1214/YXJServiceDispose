package com.youxianji.facade.product;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProductTagsRequestBean;
import com.youxianji.facade.product.bean.GetProductTagsResponseBean;
import com.youxianji.pojo.ProductTags;
import com.youxianji.service.IGetProdTagsService;
import com.youxianji.service.IGetProductTagsService;

@Facade(command="3007",comment="获取商品标签接口")
@Scope("prototype")
public class GetProductTagsFacade extends AbsFacade {
	@Resource
	private IGetProductTagsService getProductTagsService;
	@Resource
	private IGetProdTagsService getProdTagsService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProductTagsRequestBean requestBean = (GetProductTagsRequestBean)baseRequest;
		GetProductTagsResponseBean responseBean = new GetProductTagsResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<ProductTags> productTagsList = getProductTagsService.getProductTags(requestBean.getUserid(), requestBean.getTelephone(), requestBean.getTagid());
		responseBean.setProductTagsList(productTagsList);
		responseParam.getParamdata().add(responseBean);

		return responseParam;
	}

}
