package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IYxjUserCouponGrantConfigDAO;
import com.youxianji.dao.IYxjUserCouponGrantConfigDetailDAO;
import com.youxianji.dao.IYxjUserCouponShareDAO;
import com.youxianji.dao.IYxjUserCouponShareDetailDAO;
import com.youxianji.pojo.CouponRuleInfo;
import com.youxianji.pojo.YxjUserCouponGrantConfig;
import com.youxianji.pojo.YxjUserCouponGrantConfigDetail;
import com.youxianji.pojo.YxjUserCouponShare;
import com.youxianji.pojo.YxjUserCouponShareDetail;
import com.youxianji.service.IYxjUserCouponGrantConfigService;
import com.youxianji.util.DateUtil;

@Service("yxjUserCouponGrantConfigService")
public class YxjUserCouponGrantConfigServiceImpl implements IYxjUserCouponGrantConfigService {

	@Resource
	private IYxjUserCouponShareDAO yxjUserCouponShareDAO;
	
	@Resource
	private IYxjUserCouponShareDetailDAO yxjUserCouponShareDetailDAO;
	
    @Resource
    private IYxjUserCouponGrantConfigDAO yxjUserCouponGrantConfigDAO;
    
    @Resource
    private IYxjUserCouponGrantConfigDetailDAO yxjUserCouponGrantConfigDetailDAO;
	
	//支付完成发放红包
	@Override
	public void doGrantRedPacket(String shareUserId,String baseOrdersn) throws Exception{
		YxjUserCouponGrantConfig grantConfig = yxjUserCouponGrantConfigDAO.findGrantConfig("2");
		
		YxjUserCouponShare oldShare = yxjUserCouponShareDAO.findCouponShareByOrdersn(baseOrdersn);
		if(grantConfig != null && oldShare == null){
			List<YxjUserCouponGrantConfigDetail> configDetailList = yxjUserCouponGrantConfigDetailDAO.findListGrantConfigDetail(grantConfig.getConfigId());
			if(configDetailList != null && configDetailList.size() > 0){
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date currDate = sdf.parse(sdf.format(new Date()));
				
				YxjUserCouponShare share = new YxjUserCouponShare();
				share.setBaseOrdersn(baseOrdersn);
				share.setBeginTime(currDate);
				share.setEndTime(DateUtil.nextSomeDay(currDate, grantConfig.getTimeLength()-1));
				share.setShareId(UUIDGenerator.getUUID());
				share.setShareUserId(shareUserId);
				share.setTotalMoney(grantConfig.getTotalMoney());
				yxjUserCouponShareDAO.insert(share);
				
				int i = 0;
				for(YxjUserCouponGrantConfigDetail configDetail:configDetailList){
					for(i=0;i<configDetail.getGrantQuantity();i++){
						CouponRuleInfo ruleInfo = configDetail.getCouponRuleInfo();
						YxjUserCouponShareDetail shareDetail = new YxjUserCouponShareDetail();
						shareDetail.setCouponAmount(new BigDecimal(ruleInfo.getCouponMoney()));
						shareDetail.setCouponDesc(ruleInfo.getCouponDesc());
						shareDetail.setCouponId(ruleInfo.getCouponId());
						shareDetail.setCouponName(ruleInfo.getCouponName());
						shareDetail.setCouponTitle("通用红包");
						shareDetail.setDetailId(UUIDGenerator.getUUID());
						shareDetail.setShareId(share.getShareId());
						shareDetail.setSinceMoney(new BigDecimal(ruleInfo.getSinceMoney()));
						shareDetail.setBeginTime(currDate);
						shareDetail.setEndTime(DateUtil.nextSomeDay(currDate, ruleInfo.getTimeLength()-1));
						
						shareDetail.setReceiveTime(null);
						shareDetail.setReceiveUser(null);
						
						yxjUserCouponShareDetailDAO.insert(shareDetail);
					}
					
					
				}
			}
		}
		
		
	}

	@Override
	public List<YxjUserCouponGrantConfigDetail> findListGrantConfigDetail(String configId) {
		
		return yxjUserCouponGrantConfigDetailDAO.findListGrantConfigDetail(configId);
	}

	@Override
	public YxjUserCouponGrantConfig findGrantConfig(String configFlag) {
		
		return yxjUserCouponGrantConfigDAO.findGrantConfig(configFlag);
	}
	
	
}
