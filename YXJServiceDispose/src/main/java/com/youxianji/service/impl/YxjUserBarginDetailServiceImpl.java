package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.omg.CORBA.Object;
import org.springframework.stereotype.Service;

import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.dao.IYxjUserBarginDetailDAO;
import com.youxianji.dao.IYxjUserBarginInfoDAO;
import com.youxianji.facade.bargain.bean.json.AssistbargainBean;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserBarginDetail;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IYxjUserBarginDetailService;
import com.youxianji.util.Base64Util;
import com.youxianji.util.DateUtil;

@Service("yxjUserBarginDetailService")
public class YxjUserBarginDetailServiceImpl implements IYxjUserBarginDetailService{
	@Resource
	private IYxjUserBarginInfoDAO yxjUserBarginInfoDAO;
	@Resource
	private IYxjUserBarginDetailDAO yxjUserBarginDetailDAO;
	@Resource
	private IWeChatUserInfoDAO weChatUserInfoDAO;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public YxjUserBarginDetail findBargainDetail(String bargainId, String relationUserId) {
		YxjUserBarginDetail yxjUserBarginDetail=null;
		List<YxjUserBarginDetail> barginDetailList=yxjUserBarginDetailDAO.findBargainDetail(bargainId,relationUserId);
		if(barginDetailList!=null&&barginDetailList.size()>0){
			yxjUserBarginDetail=barginDetailList.get(0);
		}
		return yxjUserBarginDetail;
	}

	@Override
	public List<AssistbargainBean> findAssistbargainList(String bargainid) {
		List<YxjUserBarginDetail> barginDetailList=yxjUserBarginDetailDAO.findBargainDetailByBargainId(bargainid);
		List<AssistbargainBean> assistbargainlist=new ArrayList<>();
		if(barginDetailList!=null&&barginDetailList.size()>0){
			for (YxjUserBarginDetail yxjUserBarginDetail : barginDetailList) {
				AssistbargainBean assistbargainBean=new AssistbargainBean();
				String userId = yxjUserBarginDetail.getRelationUserId();
				WeChatUserInfo weChatUserInfo = weChatUserInfoDAO.getWeChatUserInfoByUserId(userId);
				if(weChatUserInfo != null){
					assistbargainBean.setUserid(userId);
					assistbargainBean.setHeadimgurl(weChatUserInfo.getHeadimgurl());
					String regExp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";  
			        Pattern p = Pattern.compile(regExp);  
			        if(weChatUserInfo.getNickname() != null){
			        	if(p.matcher(weChatUserInfo.getNickname()).matches()){
				        	Base64Util base64Util = new Base64Util();
				        	assistbargainBean.setNickname(base64Util.Decode(weChatUserInfo.getNickname()));
				        }else{
				        	assistbargainBean.setNickname(weChatUserInfo.getNickname());
				        }
			        }else{
			        	assistbargainBean.setNickname("");
			        }
			        
					assistbargainBean.setBargaintime(DateUtil.toStr(yxjUserBarginDetail.getBargainTime(), "yyyy-MM-dd HH:mm:ss"));
					assistbargainBean.setBargainmoney(yxjUserBarginDetail.getBarginMoney().toString());
					assistbargainlist.add(assistbargainBean);
				}
			}
		}
		return assistbargainlist;
	}

	@Override
	public void insertBargainDetail(String rulesid,String bargainid, String userid,
			YxjUserBarginInfo yxjUserBarginInfo, BigDecimal rulesMoney,
			BigDecimal totalMoney, BigDecimal leave,BigDecimal lowestMoney) {
		BigDecimal bargainMoney=null;
		if(leave.subtract(rulesMoney).doubleValue()<lowestMoney.doubleValue()){
			bargainMoney=leave.subtract(lowestMoney);
			yxjUserBarginInfo.setTotalMoney(totalMoney.add(bargainMoney));
		}else{
			bargainMoney=rulesMoney;
			yxjUserBarginInfo.setTotalMoney(totalMoney.add(bargainMoney));
		}
		
		
		YxjUserBarginDetail yxjUserBarginDetail=new YxjUserBarginDetail();
		yxjUserBarginDetail.setBargainRulesId(rulesid);
		yxjUserBarginDetail.setDetailId(UUIDGenerator.getUUID());
		yxjUserBarginDetail.setBargainId(bargainid);
		yxjUserBarginDetail.setBarginMoney(bargainMoney);
		yxjUserBarginDetail.setBargainTime(new Date());
		yxjUserBarginDetail.setRelationUserId(userid);
		try {
			yxjUserBarginDetailDAO.insertBargainDetail(yxjUserBarginDetail);
			yxjUserBarginInfoDAO.updateBargainInfo(yxjUserBarginInfo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public List<AssistbargainBean> findUserAssistBargainInfoList(String bargainid) {
		List<YxjUserBarginDetail> barginDetailList=yxjUserBarginDetailDAO.findBargainDetailByBargainId(bargainid);
		List<AssistbargainBean> assistbargainlist=new ArrayList<>();
		if(barginDetailList!=null&&barginDetailList.size()>0){
			for (YxjUserBarginDetail yxjUserBarginDetail : barginDetailList) {
				AssistbargainBean assistbargainBean=new AssistbargainBean();
				String userId = yxjUserBarginDetail.getRelationUserId();
					WeChatUserInfo weChatUserInfo = weChatUserInfoDAO.getWeChatUserInfoByUserId(userId);
					if(!ObjectTools.isNullByObject(weChatUserInfo)){
						assistbargainBean.setUserid(userId);
						assistbargainBean.setHeadimgurl(weChatUserInfo.getHeadimgurl());
						
						if(!ObjectTools.isNullByString(weChatUserInfo.getNickname())){
							String regExp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";  
					        Pattern p = Pattern.compile(regExp);  
					        if(p.matcher(weChatUserInfo.getNickname()).matches()){
					        	Base64Util base64Util = new Base64Util();
					        	assistbargainBean.setNickname(base64Util.Decode(weChatUserInfo.getNickname()));
					        }else{
					        	assistbargainBean.setNickname(weChatUserInfo.getNickname());
					        }
						}else{
							assistbargainBean.setNickname("");
						}
						assistbargainBean.setBargaintime(DateUtil.toStr(yxjUserBarginDetail.getBargainTime(), "yyyy-MM-dd HH:mm:ss"));
						assistbargainBean.setBargainmoney(yxjUserBarginDetail.getBarginMoney().toString());
						assistbargainlist.add(assistbargainBean);
					}
			}
		}
		return assistbargainlist;
	}

	@Override
	public List<YxjUserBarginDetail> findBargainDetailByBargainId(
			String bargainid) {
		return yxjUserBarginDetailDAO.findBargainDetailByBargainId(bargainid);
	}

	@Override
	public List<YxjUserBarginDetail> findBargainDetailByUser(String rulesId,
			String userid) {
		return yxjUserBarginDetailDAO.findBargainDetailByUser(rulesId,userid);
	}

}