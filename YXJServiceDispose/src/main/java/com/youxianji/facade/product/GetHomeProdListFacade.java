package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetHomeProdListRequestBean;
import com.youxianji.facade.product.bean.GetHomeProdListResponseBean;
import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.facade.product.bean.json.ProdTypeBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdType;
import com.youxianji.service.IActivityProdInfoService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="3001",comment="获取首页商品列表")
@Scope("prototype")
public class GetHomeProdListFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;
	@Resource
	private IActivityProdInfoService activityProdInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetHomeProdListRequestBean requestBean = (GetHomeProdListRequestBean)baseRequest;
		GetHomeProdListResponseBean responseBean = new GetHomeProdListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		List<ProdInfo> prodList = prodInfoService.getHomeProdInfoList();
		List<ProdInfoBean> prodBeanList = new ArrayList<ProdInfoBean>();
		ProdInfoBean prodBean = null;
		ActivityProdInfo activeInfo = null;
		for(ProdInfo pd:prodList){
			prodBean = new ProdInfoBean();
			prodBean.setProdTagsList(ptShowRelationService.getProdTags(pd.getProdid()));
			
			activeInfo =  activityProdInfoService
					.getActiveByProdId(pd.getProdid());
			
			if(activeInfo != null && "1".equals(pd.getIsactivity())){
				prodBean.setSellprice(activeInfo.getActivePrice().toString());
				prodBean.setIsactivity("1");
				prodBean.setLimitquantity(activeInfo.getLimitcount().toString());
				prodBean.setBuytype(activeInfo.getBuytype());
			}else{
				prodBean.setSellprice(pd.getSellPrice().toString());
				prodBean.setIsactivity(null);
				prodBean.setLimitquantity(null);
				prodBean.setBuytype(null);
			}
						
			prodBean.setImageurl(pd.getImageUrl());
			prodBean.setPreselltime(DateUtil.toStr(pd.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
			prodBean.setProdname(pd.getProdname());
			prodBean.setProdid(pd.getProdid());
			prodBean.setSellflag(pd.getPreSellTag());
			prodBean.setProddesc(pd.getProdDesc());
			prodBean.setUnit(pd.getProdUnit());
			prodBean.setValueprice(pd.getValuePrice().toString());
			prodBean.setFreshprice(pd.getFreshmanprice().toString());
			prodBean.setFreshflag(pd.getIsfreshman());
			prodBean.setCpackage(pd.getCpackage());
			prodBean.setProdstock(pd.getProdstock());
			prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", pd.getProdid()));
			prodBeanList.add(prodBean);
		}
		
		List<ProdType> prodTypeList = prodInfoService.getProdTypeList(null);
		List<ProdTypeBean> typeBeanList = new ArrayList<ProdTypeBean>();
		ProdTypeBean typeBean = null;
		for(ProdType pt:prodTypeList){
			 typeBean = new ProdTypeBean();
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
