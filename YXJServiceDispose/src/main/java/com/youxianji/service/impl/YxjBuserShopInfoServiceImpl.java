package com.youxianji.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjBuserShopInfoDAO;
import com.youxianji.facade.yxjia.bean.JIAGetNearByShopResponseBean;
import com.youxianji.facade.yxjia.bean.JIAGetShopDetailResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIAShopBean;
import com.youxianji.pojo.YxjBuserShopInfo;
import com.youxianji.service.IYxjBuserShopInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.PropertyManager;
@Service("yxjBuserShopInfoService")
public class YxjBuserShopInfoServiceImpl implements IYxjBuserShopInfoService {

	@Resource
	private IYxjBuserShopInfoDAO yxjBuserShopInfoDAO;
	
	static PropertyManager propertyManager = PropertyManager.instance();
	private static String shop_distance = propertyManager.getValue(Constants.YXJ_PROPERTIES,"shop_distance");
	
	@Override
	public JIAGetNearByShopResponseBean getNearByShopList(String longitude,
			String latitude) {
		JIAGetNearByShopResponseBean response = new JIAGetNearByShopResponseBean();
		List<JIAShopBean> shopBeanList = new ArrayList<JIAShopBean>();
		JIAShopBean shopBean  = null;
		List<YxjBuserShopInfo> persistShopList = yxjBuserShopInfoDAO.getNearByShopList(longitude, latitude,shop_distance);
		for(YxjBuserShopInfo shop:persistShopList){
			shopBean = new JIAShopBean();
			shopBean.setImageurl(shop.getShopImageurl());
			shopBean.setShopaddress(shop.getShopAddress());
			shopBean.setShopid(shop.getShopId());
			shopBean.setShopname(shop.getShopName());
			shopBean.setShoptelephone(shop.getShopTelephone());
			
			shopBeanList.add(shopBean);
		}
		response.setShoplist(shopBeanList);
		return response;
	}

	@Override
	public JIAGetShopDetailResponseBean getShopDetail(String shopid) {
		JIAGetShopDetailResponseBean response = new JIAGetShopDetailResponseBean();
		YxjBuserShopInfo shop = yxjBuserShopInfoDAO.getShopDetail(shopid);

		response.setBusinesstime(shop.getShopBusinessbegin()+"-"+shop.getShopBusinessend());
		response.setImageurl(shop.getShopImageurl());
		response.setShopaddress(shop.getShopAddress());
		response.setShopid(shopid);
		response.setShopname(shop.getShopName());
		response.setShoptelephone(shop.getShopTelephone());
		response.setUserid(shop.getUserinfoUserId());
		
		return response;
	}

	@Override
	public YxjBuserShopInfo getShopDetailByUserId(String userId) {
		// TODO Auto-generated method stub
		return yxjBuserShopInfoDAO.getShopDetailByuserId(userId);
	}
	

}
