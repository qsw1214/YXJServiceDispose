package com.youxianji.facade.groupbuy;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.groupbuy.bean.GetGroupbuyProdInfoRequestBean;
import com.youxianji.facade.groupbuy.bean.GetGroupbuyProdInfoResponseBean;
import com.youxianji.facade.groupbuy.bean.json.GroupbuyProdInfoBean;
import com.youxianji.pojo.Groupbuy;
import com.youxianji.pojo.GroupbuyDetail;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IGroupbuyDetailService;
import com.youxianji.service.IGroupbuyService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Facade(command="3014",comment="获取社区团购商品列表")
@Scope("prototype")
public class GetGroupbuyProdInfoFacade extends AbsFacade{

	@Resource
	private IGroupbuyService groupbuyService;
	@Resource
	private IGroupbuyDetailService groupbuyDetailService;
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetGroupbuyProdInfoRequestBean requestBean = (GetGroupbuyProdInfoRequestBean)baseRequest;
		GetGroupbuyProdInfoResponseBean responseBean = new GetGroupbuyProdInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
			List<GroupbuyProdInfoBean> prodBeanList = new ArrayList<GroupbuyProdInfoBean>();
			GroupbuyProdInfoBean prodBean = null;
			//是否存在活动
			String isenable="1";
			Groupbuy groupbuy=groupbuyService.getGroupbuyByDate();
			if(groupbuy == null){
				isenable="0";
			}else{
				//获取团购商品
				List<GroupbuyDetail> groupbuyDetailList = groupbuyDetailService.findGroupbuyDetailByGroupbuyId(groupbuy.getId());
				for (GroupbuyDetail groupbuyDetail : groupbuyDetailList) {
					ProdInfo prodInfo = prodInfoService.getProdInfoById(groupbuyDetail.getProdid());
					prodBean = new GroupbuyProdInfoBean();
					prodBean.setGroupbuyprice(groupbuyDetail.getGroupbuyPrice().toString());
					prodBean.setProdTagsList(ptShowRelationService.getProdTags(prodInfo.getProdid()));
					prodBean.setBuytype(null);
					prodBean.setImageurl(prodInfo.getImageUrl());
					prodBean.setIsactivity(null);
					prodBean.setLimitquantity(null);
					prodBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
					prodBean.setProdname(prodInfo.getProdname());
					prodBean.setProdid(prodInfo.getProdid());
					prodBean.setSellflag(prodInfo.getPreSellTag());
					prodBean.setProddesc(prodInfo.getProdDesc());
					prodBean.setUnit(prodInfo.getProdUnit());
					prodBean.setValueprice(prodInfo.getValuePrice().toString());
					prodBean.setSellprice(prodInfo.getSellPrice().toString());
					prodBean.setFreshprice(prodInfo.getFreshmanprice().toString());
					prodBean.setFreshflag(prodInfo.getIsfreshman());
					prodBean.setCpackage(prodInfo.getCpackage());
					prodBean.setProdstock(prodInfo.getProdstock());
					prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", prodInfo.getProdid()));
					prodBean.setSpecialFlag(groupbuyDetail.getSpecialFlag());
					prodBeanList.add(prodBean);
				}
				responseBean.setGroupbuyid(groupbuy.getId());
				responseBean.setProdlist(prodBeanList);
			}
			//返回内容
			responseBean.setIsenable(isenable);
			responseParam.getParamdata().add(responseBean);
			return responseParam;
		
	}
	
}
