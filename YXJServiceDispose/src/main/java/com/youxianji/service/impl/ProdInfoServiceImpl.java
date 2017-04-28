package com.youxianji.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.IProdTagInfoDAO;
import com.youxianji.dao.IProdTypeDAO;
import com.youxianji.dao.IPtShowRelationDao;
import com.youxianji.facade.product.bean.GetNewHomeProdRequestBean;
import com.youxianji.facade.product.bean.GetNewHomeProdResponseBean;
import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.facade.product.bean.json.TagBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.ProdTagInfo;
import com.youxianji.pojo.ProdType;
import com.youxianji.service.IProdInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;

@Service("prodInfoService")
public class ProdInfoServiceImpl implements IProdInfoService {

	@Resource
	private IProdInfoDAO prodInfoDAO;
	@Resource
	private IProdTypeDAO prodTypeDAO;
	@Resource
	private IProdTagInfoDAO prodTagInfoDAO;
	@Resource
	private IPtShowRelationDao ptShowRelationDao;
	@Resource
	private IOrderDetailDAO orderDetailDAO;
	@Resource
	private IActivityProdInfoDAO activityProdInfoDAO;

	@Override
	public List<ProdInfo> getProdInfoList() {

		return prodInfoDAO.getProdInfoList();
	}

	@Override
	public List<ProdType> getProdTypeList(String parentid) {
		Map<String,String> params = new HashMap<String,String>();
		params.put("parentid", parentid);
		return prodTypeDAO.getProdTypeList(params);
	}

	@Override
	public List<ProdInfo> getProdInfoListByType(String type) {
		
		return prodInfoDAO.getProdInfoListByType(type);
	}

	@Override
	public ProdInfo getProdInfoById(String prodId) {
		
		return prodInfoDAO.getById(prodId);
	}

	@Override
	public PageInfo<ProdInfo> getPageListProdInfo(Map<String, String> paramsMap) {
		PageInterceptor.startPage(Integer.parseInt(paramsMap.get("page")));
		prodInfoDAO.getProdInfoListByParamsMap(paramsMap);
		return PageInterceptor.endPage();
	}

	@Override
	public List<ProdInfo> getHomeProdInfoList() {
		Map<String, String> paramsMap = new HashMap<String,String>();
		paramsMap.put("ishome", "1");
		return prodInfoDAO.getProdInfoListByParamsMap(paramsMap);
	}

	@Override
	public List<ProdInfo> getProdListBySearchName(String searchName) {

		
		return prodInfoDAO.getProdListBySearchName(searchName);
	}

	@Override
	public GetNewHomeProdResponseBean getNewHomeProdList(
			GetNewHomeProdRequestBean request) {
		GetNewHomeProdResponseBean responseBean = new GetNewHomeProdResponseBean();

		List<TagBean> tagBeanlist = new ArrayList<TagBean>();
		TagBean tagBean = null;
		
		List<ProdInfoBean> prodBeanList = null;
		ProdInfoBean prodBean = null;
		
		List<ProdInfo> persistProdList = new ArrayList<ProdInfo>();
		
		List<ProdTagInfo> persistTaglist = prodTagInfoDAO.getList();
		ActivityProdInfo activeInfo = null;
		for(ProdTagInfo persistTag:persistTaglist){
			prodBeanList =  new ArrayList<ProdInfoBean>();
					
			tagBean = new TagBean();
			tagBean.setTagid(persistTag.getTagId());
			tagBean.setTagname(persistTag.getTagName());
			tagBean.setTagurl(persistTag.getTagurl());
			
			persistProdList = prodInfoDAO.getProdListByTags(persistTag.getTagId());
			
			for(ProdInfo pd:persistProdList){
				prodBean = new ProdInfoBean();
				prodBean.setProdTagsList(ptShowRelationDao.getProdTags(pd.getProdid()));
				
				activeInfo =  activityProdInfoDAO
						.getActiveByProdId(pd.getProdid());
				if(activeInfo != null && "1".equals(pd.getIsactivity()) ){
					prodBean.setSellprice(activeInfo.getActivePrice().toString());
					prodBean.setIsactivity("1");
					prodBean.setLimitquantity(activeInfo.getLimitcount().toString());
					prodBean.setBuytype(activeInfo.getBuytype());
				}else{
					prodBean.setSellprice(pd.getSellPrice().toString());
					prodBean.setBuytype(null);
					prodBean.setIsactivity(null);
					prodBean.setLimitquantity(null);
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
				prodBean.setIshome(pd.getIshome());
				prodBeanList.add(prodBean);
			}
			tagBean.setProdlist(prodBeanList);
			
			tagBeanlist.add(tagBean);
		}
		
		responseBean.setTaglist(tagBeanlist);
		return responseBean;
	}

	@Override
	public void updateAddStock(String ordersn) {
		//更新商品库存
		List<OrderDetail> orderDetailList = orderDetailDAO.getDetailListByOrderSn(ordersn);
	    for(OrderDetail od:orderDetailList){
	    	 if(od.getProd().getProdstock() != null && od.getProd().getProdstock() != -9999 ){
	     	     prodInfoDAO.updateAddStock(od.getProd().getProdid(), String.valueOf(od.getQuantity()));
		      }
	    	 
	     }
	}

	@Override
	public List<ProdInfo> getJIAHomeProdInfoList() {
		Map<String, String> paramsMap = new HashMap<String,String>();
		/*paramsMap.put("ishome", "1");*/
		paramsMap.put("typeflag", "B");
		return prodInfoDAO.getProdInfoListByParamsMap(paramsMap);
	}
		
}
