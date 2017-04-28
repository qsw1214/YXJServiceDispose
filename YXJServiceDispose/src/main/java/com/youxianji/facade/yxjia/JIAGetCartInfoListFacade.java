package com.youxianji.facade.yxjia;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetCartInfoRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetCartInfoResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIACartInfoBean;
import com.youxianji.facade.yxjia.bean.json.JIAProdTagsBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdTags;
import com.youxianji.pojo.YxjBuserCartInfo;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.service.IYxjBuserCartInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="8001",comment="JIA-获取购物车")
@Scope("prototype")
public class JIAGetCartInfoListFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserCartInfoService yxjBuserCartInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetCartInfoResponseBean responseBean = new JIAGetCartInfoResponseBean();
		JIAGetCartInfoRequestBean requestBean = (JIAGetCartInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        String userId = requestBean.getUserid();
		List<YxjBuserCartInfo> cartInfoList = yxjBuserCartInfoService.getListYxjBuserCartInfoByUserId(userId);
		List<JIACartInfoBean> cfBeanList = new ArrayList<JIACartInfoBean>();
		JIACartInfoBean cfBean = null;
		JIAProdTagsBean prodTagsBean = null;
		List<JIAProdTagsBean> prodTagsBeanList = null;
		Integer totalQuantity = 0;
		ProdInfo prodInfo = null;
		BigDecimal actuPrice = null;
		for(YxjBuserCartInfo ci:cartInfoList){
			cfBean = new JIACartInfoBean();
			prodInfo = ci.getProdInfo();
			
			prodTagsBeanList = new ArrayList<JIAProdTagsBean>();
			for(ProdTags prodTags:ptShowRelationService.getProdTags(prodInfo.getProdid())){
				prodTagsBean = new JIAProdTagsBean();
				prodTagsBean.setTagid(prodTags.getTagId());
				prodTagsBean.setTagname(prodTags.getTagName());
				prodTagsBeanList.add(prodTagsBean);
			}
			cfBean.setProdtagslist(prodTagsBeanList);
			
			
			cfBean.setImageur(prodInfo.getImageUrl());
			cfBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
			cfBean.setProdname(ci.getProdName());
			cfBean.setProdid(ci.getProdInfo().getProdid());
			cfBean.setCartid(ci.getCartId());
			cfBean.setQuantity(ci.getProdQuantity().toString());
			cfBean.setSellflag(prodInfo.getPreSellTag());
			
			actuPrice = yxjBuserCartInfoService.getActualPrice(prodInfo);
			cfBean.setSellprice(actuPrice.toString());
			cfBean.setValueprice(prodInfo.getValuePrice().toString());
			cfBean.setMemberprice(prodInfo.getSellPrice().toString());
			
			cfBean.setTotalprice(actuPrice.multiply(new BigDecimal(ci.getProdQuantity().toString())).toString());
			
			
			cfBean.setFreshflag(prodInfo.getIsfreshman());
			cfBean.setFreshprice(prodInfo.getFreshmanprice().toString());
			
			cfBean.setUnit(prodInfo.getProdUnit());
			cfBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID",ci.getProdInfo().getProdid()));
			
			totalQuantity = totalQuantity +ci.getProdQuantity();
			cfBeanList.add(cfBean);
		}
		responseBean.setCartquantity(totalQuantity.toString());
		responseBean.setCartlist(cfBeanList);
		responseBean.setTotalprice(yxjBuserCartInfoService
				.getCartActualTotalPrice(cartInfoList).toString());
		responseBean.setMembertotalprice(yxjBuserCartInfoService
				.getCartMemberTotalPrice(cartInfoList).toString());
						
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
