package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetHomeADListRequestBean;
import com.youxianji.facade.product.bean.GetHomeADListResponseBean;
import com.youxianji.facade.product.bean.json.HomeADBean;
import com.youxianji.pojo.HomeAd;
import com.youxianji.service.IHomeAdService;
import com.youxianji.web.util.CommonMobileResponse;

@Facade(command="3005",comment="获取首页广告接口")
@Scope("prototype")
public class GetHomeADListFacade extends AbsFacade{
	
	@Resource
	private IHomeAdService homeAdService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetHomeADListRequestBean requestBean = (GetHomeADListRequestBean)baseRequest;
		GetHomeADListResponseBean responseBean = new GetHomeADListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<HomeAd> homeAdList = homeAdService.getHomeAdList();
		List<HomeADBean> adBeanList = new ArrayList<HomeADBean>();
		HomeADBean adBean = null;
		for(HomeAd homeAd:homeAdList){
			adBean = new HomeADBean();
			adBean.setAdid(homeAd.getAdid());
			adBean.setAdtitle(homeAd.getAdtitle());
			adBean.setImageurl(homeAd.getAdurl());
			adBean.setLinkurl(homeAd.getLinkUrl());
			adBeanList.add(adBean);
		}
		responseBean.setAdlist(adBeanList);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
		
		
		
	}

}
