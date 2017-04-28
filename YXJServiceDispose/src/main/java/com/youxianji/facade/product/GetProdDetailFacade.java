package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProdDetailRequestBean;
import com.youxianji.facade.product.bean.GetProdDetailResponseBean;
import com.youxianji.facade.product.bean.json.ProdDetailBean;
import com.youxianji.facade.product.bean.json.ProdImageBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.Groupbuy;
import com.youxianji.pojo.GroupbuyDetail;
import com.youxianji.pojo.ProdImageInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IActivityProdInfoService;
import com.youxianji.service.IGroupbuyDetailService;
import com.youxianji.service.IGroupbuyService;
import com.youxianji.service.IOrderDetailService;
import com.youxianji.service.IProdDetailInfoService;
import com.youxianji.service.IProdImageInfoService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="3004",comment="获取商品详情")
@Scope("prototype")
public class GetProdDetailFacade extends AbsFacade{
	
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IProdImageInfoService prodImageInfoService;
	@Resource
	private IProdDetailInfoService prodDetailInfoService;
	@Resource
	private IOrderDetailService orderDetailService;
	@Resource
	private IActivityProdInfoService activityProdInfoService;
	@Resource
	private IPtShowRelationService ptShowRelationService;
	@Resource
	private IGroupbuyDetailService groupbuyDetailService;
	@Resource
	private IGroupbuyService groupbuyService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProdDetailRequestBean requestBean = (GetProdDetailRequestBean)baseRequest;
		GetProdDetailResponseBean responseBean = new GetProdDetailResponseBean();
		BaseResponse responseParam = new BaseResponse();
		String prodId = requestBean.getProdid();
		if(ObjectTools.isNullByString(prodId)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("产品ID不能传空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		ProdInfo prodInfo = prodInfoService.getProdInfoById(prodId);
		responseBean.setProdTagsList(ptShowRelationService.getProdTags(prodInfo.getProdid()));
		
		ActivityProdInfo activeInfo =  activityProdInfoService.getActiveByProdId(prodInfo.getProdid());
		if(activeInfo != null  && "1".equals(prodInfo.getIsactivity())){
			responseBean.setSellprice(activeInfo.getActivePrice().toString());
			responseBean.setBuytype(null);
			responseBean.setIsactivity("1");
			responseBean.setLimitquantity(activeInfo.getLimitcount().toString());
			responseBean.setActivityendtime(DateUtil.toStr(activeInfo.getActivityend(), "yyyy-MM-dd HH:mm:ss"));
		}else{
			responseBean.setSellprice(prodInfo.getSellPrice().toString());
			responseBean.setBuytype(null);
			responseBean.setIsactivity(null);
			responseBean.setLimitquantity(null);
			responseBean.setActivityendtime(null);
		}
		//获取当前团购规则
		Groupbuy groupbuy=groupbuyService.getGroupbuyByDate();
		if(groupbuy!=null){
			//获取团购商品
			GroupbuyDetail groupbuyDetail =groupbuyDetailService.findGroupbuyDetailByProdid(groupbuy.getId(),prodId);
			if(groupbuyDetail!=null){
					responseBean.setGroupbuyid(groupbuy.getId());
					responseBean.setGroupbuyprice(groupbuyDetail.getGroupbuyPrice().toString());
			}	
		}
		Date preSellTime = prodInfo.getPreSellTime();
		responseBean.setPreselltime(preSellTime == null ? "":DateUtil.toStr(preSellTime,"yyyy-MM-dd HH:mm:ss"));
		responseBean.setProdname(prodInfo.getProdname());
		responseBean.setProdid(prodInfo.getProdid());
		responseBean.setProddesc(prodInfo.getProdDesc());
		responseBean.setSellflag(prodInfo.getPreSellTag());
		responseBean.setUnit(prodInfo.getProdUnit());
		responseBean.setValueprice(prodInfo.getValuePrice().toString());
		responseBean.setFreshprice(prodInfo.getFreshmanprice().toString());
		responseBean.setFreshflag(prodInfo.getIsfreshman());
		responseBean.setCpackage(prodInfo.getCpackage());
		responseBean.setProdstock(prodInfo.getProdstock());
		Map<String,String> map = new HashMap<String,String>();
		map.put("prodId", prodId);
		Calendar cDay1 = Calendar.getInstance();  
        int lastDay = cDay1.getActualMaximum(Calendar.DAY_OF_MONTH);  
        
		map.put("beginTime", DateUtil.toStr(cDay1.getTime(), "yyyy-MM")+" 01 00:00:00");
		map.put("endTime", DateUtil.toStr(cDay1.getTime(), "yyyy-MM")+"-"+lastDay+" 23:59:59");
		String sellcount = orderDetailService.getMonthSellCountByProd(map);
		responseBean.setMonthsellcount(sellcount);
		
		List<ProdImageInfo> prodImageList = prodImageInfoService.getListProdImageInfoByProdId(prodId);
		List<ProdImageBean> imageBeanList = new ArrayList<ProdImageBean>();
		ProdImageBean imageBean = null;
		for(ProdImageInfo pi:prodImageList){
			imageBean = new ProdImageBean();
			imageBean.setImageid(pi.getProdImageId());
			imageBean.setImagepath(pi.getProdImageUrl());
			imageBeanList.add(imageBean);
		}
		ProdDetailBean detailBean = new ProdDetailBean();
		if(prodInfo.getDetailInfo() != null){
			detailBean.setDetailid(prodInfo.getDetailInfo().getDetailId());
			detailBean.setDescurl(prodInfo.getDetailInfo().getTextString());
		}
		responseBean.setImageinfo(imageBeanList);
		responseBean.setProddetail(detailBean);
		
		responseBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", prodInfo.getProdid()));
			
		responseParam.getParamdata().add(responseBean);
        return responseParam;
	}

}
