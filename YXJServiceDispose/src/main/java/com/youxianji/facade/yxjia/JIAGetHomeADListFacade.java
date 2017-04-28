package com.youxianji.facade.yxjia;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetHomeADListRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetHomeADListResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIAHomeADBean;
import com.youxianji.pojo.YxjBuserHomeAd;
import com.youxianji.service.IYxjBuserHomeAdService;

@Facade(command="5002",comment="JIA-获取首页广告接口")
@Scope("prototype")
public class JIAGetHomeADListFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserHomeAdService yxjBuserHomeAdService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetHomeADListRequestBean requestBean = (JIAGetHomeADListRequestBean)baseRequest;
		JIAGetHomeADListResponseBean responseBean = new JIAGetHomeADListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<YxjBuserHomeAd> homeAdList = yxjBuserHomeAdService.getYxjBuserHomeAdList();
		List<JIAHomeADBean> adBeanList = new ArrayList<JIAHomeADBean>();
		JIAHomeADBean adBean = null;
		for(YxjBuserHomeAd homeAd:homeAdList){
			adBean = new JIAHomeADBean();
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
