package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.IFreshmanProductDao;
import com.youxianji.dao.IYxjUserEmployeeDAO;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IFreshmanProductService;
import com.youxianji.web.util.ErrorEnum;
@Service("freshmanProductService")
public class FreshmanProductServiceImpl implements IFreshmanProductService {
	@Resource
	private IFreshmanProductDao freshmanProductDao;
	@Resource
	private IActivityProdInfoDAO activityProdInfoDAO;
	@Resource
	private IYxjUserEmployeeDAO yxjUserEmployeeDAO; 

	@Override
	public String findIsfreshmanByIdAndTelephone(String userid, String telephone) {
		
		return freshmanProductDao.findIsfreshmanByIdAndTelephone(userid, telephone);
	}
	@Override
	public List<ProdInfo> getFreshmanProductList() {
		return freshmanProductDao.getFreshmanProductList();
	}
	@Override
	public List<ProdInfo> getRecommendProductList(String tag) {
		
		return freshmanProductDao.getRecommendProductList(tag);
	}
	@Override
	public List<ProdInfo> getProdByTypetList(String typeId) {
		return freshmanProductDao.getProdByTypetList(typeId);
	}
	@Override
	public List<ProdInfo> getPennyProductList(String employnum) throws BaseException {
		if(yxjUserEmployeeDAO.getObject(employnum) == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("请输入正确的体验邀请码");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		return activityProdInfoDAO.getPennyProductList();
	}

}
