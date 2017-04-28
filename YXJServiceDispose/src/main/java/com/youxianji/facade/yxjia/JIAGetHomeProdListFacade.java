package com.youxianji.facade.yxjia;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetHomeProdListRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetHomeProdListResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIAProdInfoBean;
import com.youxianji.facade.yxjia.bean.json.JIAProdTagsBean;
import com.youxianji.facade.yxjia.bean.json.JIAProdTypeBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdTags;
import com.youxianji.pojo.ProdType;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="7001",comment="JIA-获取首页商品列表")
@Scope("prototype")
public class JIAGetHomeProdListFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetHomeProdListRequestBean requestBean = (JIAGetHomeProdListRequestBean)baseRequest;
		JIAGetHomeProdListResponseBean responseBean = new JIAGetHomeProdListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<ProdInfo> prodList = prodInfoService.getJIAHomeProdInfoList();
		List<JIAProdInfoBean> prodBeanList = new ArrayList<JIAProdInfoBean>();
		JIAProdInfoBean prodBean = null;
		List<JIAProdTagsBean> prodTagsBeanList = null;
		JIAProdTagsBean prodTagsBean = null;
		for(ProdInfo pd:prodList){
			prodBean = new JIAProdInfoBean();
			
			prodTagsBeanList = new ArrayList<JIAProdTagsBean>();
			for(ProdTags prodTags:ptShowRelationService.getProdTags(pd.getProdid())){
				prodTagsBean = new JIAProdTagsBean();
				prodTagsBean.setTagid(prodTags.getTagId());
				prodTagsBean.setTagname(prodTags.getTagName());
				prodTagsBeanList.add(prodTagsBean);
			}
			prodBean.setProdtagslist(prodTagsBeanList);
			
			
			
			
			prodBean.setBuytype(null);
			prodBean.setImageurl(pd.getImageUrl());
			prodBean.setIsactivity(null);
			prodBean.setLimitquantity(null);
			prodBean.setPreselltime(DateUtil.toStr(pd.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
			prodBean.setProdname(pd.getProdname());
			prodBean.setProdid(pd.getProdid());
			prodBean.setSellflag(pd.getPreSellTag());
			prodBean.setProddesc(pd.getProdDesc());
			prodBean.setUnit(pd.getProdUnit());
			prodBean.setValueprice(pd.getValuePrice().toString());
			prodBean.setSellprice(pd.getSellPrice().toString());
			prodBean.setFreshprice(pd.getFreshmanprice().toString());
			prodBean.setFreshflag(pd.getIsfreshman());
			prodBean.setCpackage(pd.getCpackage());
			prodBean.setProdstock(pd.getProdstock());
			prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", pd.getProdid()));
			prodBeanList.add(prodBean);
		}
		
		List<ProdType> prodTypeList = prodInfoService.getProdTypeList(null);
		List<JIAProdTypeBean> typeBeanList = new ArrayList<JIAProdTypeBean>();
		JIAProdTypeBean typeBean = null;
		for(ProdType pt:prodTypeList){
			 typeBean = new JIAProdTypeBean();
			 typeBean.setTypeid(pt.getTypeid());
			 typeBean.setTypename(pt.getTypename());
			 typeBeanList.add(typeBean);
		}
		responseBean.setProdlist(prodBeanList);
		responseBean.setTypelist(typeBeanList);
			
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
