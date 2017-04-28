package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProdListBySearchNameRequestBean;
import com.youxianji.facade.product.bean.GetProdListBySearchNameResponseBean;
import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IActivityProdInfoService;
import com.youxianji.service.IHistorySeekService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="3009",comment="按搜索关键字获取商品列表")
@Scope("prototype")
public class GetProdListBySearchNameFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IHistorySeekService historySeekService;
	@Resource
	private IPtShowRelationService ptShowRelationService;
	@Resource
	private IActivityProdInfoService activityProdInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProdListBySearchNameResponseBean responseBean = new GetProdListBySearchNameResponseBean();
		GetProdListBySearchNameRequestBean requestBean = (GetProdListBySearchNameRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String searchName = requestBean.getSearchword();
		if(ObjectTools.isNullByString(searchName)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("请输入搜索词");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
			
			
		List<ProdInfo> prodList = prodInfoService.getProdListBySearchName(searchName);
		List<ProdInfoBean> prodBeanList = new ArrayList<ProdInfoBean>();
		ProdInfoBean prodBean = null;
		ActivityProdInfo activeInfo = null;
		if(prodBeanList != null){
			
			for(ProdInfo pd:prodList){
				prodBean = new ProdInfoBean();
				prodBean.setProdTagsList(ptShowRelationService.getProdTags(pd.getProdid()));
				prodBean.setImageurl(pd.getImageUrl());
				
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
				
				prodBean.setPreselltime(DateUtil.toStr(pd.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
				prodBean.setProdname(pd.getProdname());
				prodBean.setProddesc(pd.getProdDesc());
				prodBean.setProdid(pd.getProdid());
				prodBean.setSellflag(pd.getPreSellTag());
				prodBean.setUnit(pd.getProdUnit());
				prodBean.setValueprice(pd.getValuePrice().toString());
				
				
				
				prodBean.setFreshprice(pd.getFreshmanprice().toEngineeringString());
				prodBean.setFreshflag(pd.getIsfreshman());
				prodBean.setCpackage(pd.getCpackage());
				prodBean.setProdstock(pd.getProdstock());
				prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", pd.getProdid()));

				prodBeanList.add(prodBean);
			}
			responseBean.setProdlist(prodBeanList);
			
			
			//查询历史记录表中是否包含，如果不包含则添加
			if(!ObjectTools.isNullByString(requestBean.getUserid())){
				historySeekService.saveHistorySeek(requestBean.getUserid(), searchName);
			}
		}
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
