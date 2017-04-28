package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.facade.product.bean.GetProdListByCategoryRequestBean;
import com.youxianji.facade.product.bean.GetProdListByCategoryResponseBean;
import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IActivityProdInfoService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="3003",comment="按类别获取商品列表")
@Scope("prototype")
public class GetProdListByCategoryFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;
	@Resource
	private IActivityProdInfoService activityProdInfoService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProdListByCategoryRequestBean requestBean = (GetProdListByCategoryRequestBean)baseRequest;
		GetProdListByCategoryResponseBean responseBean = new GetProdListByCategoryResponseBean();
		BaseResponse responseParam = new BaseResponse();
		String typeId = requestBean.getTypeid();
		String typeLevel = requestBean.getTypelevel();
		String page = requestBean.getPage();
		Map<String,String> paramsMap = new HashMap<String,String>();
		paramsMap.put("typeId", typeId);
		paramsMap.put("typeflag", "C");
		paramsMap.put("typeLevel", typeLevel);
		paramsMap.put("page", page);
		
		PageInfo<ProdInfo> prodInfoPage = prodInfoService.getPageListProdInfo(paramsMap);
		responseBean.setCurrentpage(prodInfoPage.getPageIndex().toString());
		responseBean.setPagecount(prodInfoPage.getPageSize().toString());
		responseBean.setTotalcount(prodInfoPage.getRecordCount().toString());
		responseBean.setTotalpage(prodInfoPage.getTotalpage().toString());
		List<ProdInfoBean> prodBeanList = new ArrayList<ProdInfoBean>();
		buildListProdInfoBean(prodInfoPage.getList(),prodBeanList);
		responseBean.setProdlist(prodBeanList);
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildListProdInfoBean(List<ProdInfo> prodList,List<ProdInfoBean> prodBeanList){
		
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
			prodBean.setProddesc(pd.getProdDesc());
			prodBean.setSellflag(pd.getPreSellTag());
			prodBean.setUnit(pd.getProdUnit());
			prodBean.setValueprice(pd.getValuePrice().toString());
			prodBean.setFreshprice(pd.getFreshmanprice().toString());
			prodBean.setFreshflag(pd.getIsfreshman());
			prodBean.setCpackage(pd.getCpackage());
			prodBean.setProdstock(pd.getProdstock());
			prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", pd.getProdid()));

			prodBeanList.add(prodBean);
		}
		
		
	}

}
