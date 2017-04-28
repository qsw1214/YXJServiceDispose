package com.youxianji.facade.trade;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.GetCartInfoRequestBean;
import com.youxianji.facade.trade.bean.GetCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartInfoBean;
import com.youxianji.pojo.CartInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.ICartInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="4001",comment="获取购物车")
@Scope("prototype")
public class GetCartInfoListFacade extends AbsFacade{
	
	@Resource
	private ICartInfoService cartInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetCartInfoResponseBean responseBean = new GetCartInfoResponseBean();
		GetCartInfoRequestBean requestBean = (GetCartInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        String userId = requestBean.getUserid();
		List<CartInfo> cartInfoList = cartInfoService.getListCartInfoByUserId(userId);
		List<CartInfoBean> cfBeanList = new ArrayList<CartInfoBean>();
		CartInfoBean cfBean = null;
		Integer totalQuantity = 0;
		ProdInfo prodInfo = null;
		for(CartInfo ci:cartInfoList){
			cfBean = new CartInfoBean();
			prodInfo = ci.getProdInfo();
			cfBean.setProdTagsList(ptShowRelationService.getProdTags(prodInfo.getProdid()));
			cfBean.setImageur(ci.getImageUrl());
			cfBean.setPreselltime(DateUtil.toStr(ci.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
			cfBean.setProdname(ci.getProdName());
			cfBean.setProdid(ci.getProdInfo().getProdid());
			cfBean.setCartid(ci.getCartId());
			cfBean.setQuantity(ci.getProdQuantity().toString());
			cfBean.setSellflag(ci.getPreSellTag());
			
			cfBean.setSellprice(cartInfoService.getActualPrice(prodInfo).toString());
			cfBean.setValueprice(prodInfo.getValuePrice().toString());
			cfBean.setMemberprice(prodInfo.getSellPrice().toString());
			
			cfBean.setFreshflag(prodInfo.getIsfreshman());
			cfBean.setFreshprice(prodInfo.getFreshmanprice().toString());
			
			cfBean.setUnit(ci.getProdUnit());
			cfBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID",ci.getProdInfo().getProdid()));
			
			totalQuantity = totalQuantity +ci.getProdQuantity();
			cfBeanList.add(cfBean);
		}
		responseBean.setCartquantity(totalQuantity.toString());
		responseBean.setCartlist(cfBeanList);
		responseBean.setTotalprice(cartInfoService
				.getCartMemberTotalPrice(cartInfoList).toString());
		responseBean.setMembertotalprice(cartInfoService
				.getCartMemberTotalPrice(cartInfoList).toString());
						
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
