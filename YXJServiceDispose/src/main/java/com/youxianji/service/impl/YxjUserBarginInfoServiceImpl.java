package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IYxjUserBarginInfoDAO;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.service.IYxjUserBarginInfoService;

@Service("yxjUserBarginInfoService")
public class YxjUserBarginInfoServiceImpl implements IYxjUserBarginInfoService {
	@Resource
	private IYxjUserBarginInfoDAO yxjUserBarginInfoDAO;

	@Override
	public YxjUserBarginInfo findBargainInfoById(String bargainid) {
		return yxjUserBarginInfoDAO.findBargainInfoById(bargainid);
	}

	@Override
	public String insertBargainInfo(String rulesid, String userid,String remark) {
		YxjUserBarginInfo yxjUserBarginInfo=new YxjUserBarginInfo();
		String bargainid=UUIDGenerator.getUUID();
		yxjUserBarginInfo.setId(bargainid);
		yxjUserBarginInfo.setRulesId(rulesid);
		yxjUserBarginInfo.setUserId(userid);
		yxjUserBarginInfo.setTotalMoney(new BigDecimal(0));
		yxjUserBarginInfo.setRemark(remark);
		yxjUserBarginInfo.setCreateTime(new Date());
		yxjUserBarginInfoDAO.insertBargainInfo(yxjUserBarginInfo);
		return bargainid;
	}

	@Override
	public YxjUserBarginInfo findBargainInfoByUserId(String rulesid,String userid) {
		YxjUserBarginInfo yxjUserBarginInfo=null;
		List<YxjUserBarginInfo> barginInfoList=yxjUserBarginInfoDAO.findBargainInfoByUserId(rulesid,userid);
		if(barginInfoList!=null&&barginInfoList.size()>0){
			yxjUserBarginInfo=barginInfoList.get(0);
		}
		return yxjUserBarginInfo;
	}

	@Override
	public void updateBargainInfo(YxjUserBarginInfo yxjUserBarginInfo) {
		yxjUserBarginInfoDAO.updateBargainInfo(yxjUserBarginInfo);
		
	}
}