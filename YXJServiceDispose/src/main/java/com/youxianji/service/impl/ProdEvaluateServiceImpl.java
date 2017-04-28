package com.youxianji.service.impl;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IProdEvaluateDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IYxjUserProdevaluateReplyDAO;
import com.youxianji.facade.product.bean.AddProdEvaluateRequestBean;
import com.youxianji.facade.product.bean.AddProdEvaluateResponseBean;
import com.youxianji.facade.product.bean.GetProdEvaluateRequestBean;
import com.youxianji.facade.product.bean.GetProdEvaluateResponseBean;
import com.youxianji.facade.product.bean.json.ProdEvaluateBean;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.ProdEvaluate;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjUserProdevaluateReply;
import com.youxianji.service.IProdEvaluateService;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;
import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;

@Service("prodEvaluateService")
public class ProdEvaluateServiceImpl implements IProdEvaluateService {

	@Resource
	private IProdEvaluateDAO prodEvaluateDAO;
	@Resource
	private IUserInfoDAO userInfoDAO;
	@Resource
	private IOrderDetailDAO orderDetailDAO;
	@Resource
	private IYxjUserProdevaluateReplyDAO yxjUserProdevaluateReplyDAO;

	@Override
	public AddProdEvaluateResponseBean addProdEvaluate(
			AddProdEvaluateRequestBean request) throws BaseException {
		AddProdEvaluateResponseBean responseBean = new AddProdEvaluateResponseBean();
		
		check(request);
		
		
		prodEvaluateDAO.insert(buildProdEvaluate(request));
        
		//修改制定订单明细商品为已评价
		List<OrderDetail> orderDetail = orderDetailDAO.getByProdId(request.getOrdersn(), request.getProdid());
		for(OrderDetail od:orderDetail){
			od.setIsreview("1");
			orderDetailDAO.doSwitchOrderDetailPrice(od);
			
		}
	
		return responseBean;
	}
	
	private ProdEvaluate buildProdEvaluate(AddProdEvaluateRequestBean request) throws BaseException{
		UserInfo userInfo = userInfoDAO.getById(request.getUserid());
			
		ProdEvaluate pv = new ProdEvaluate();
		pv.setCreatetime(new Date());
        pv.setId(UUIDGenerator.getUUID());
        pv.setProdevaluate(request.getProdevaluate());
        pv.setStarlevel(Integer.valueOf(request.getStarlevel()));
        pv.setState("1");
        pv.setTelephone(userInfo.getTelephone());
        pv.setUserid(userInfo.getUserId());
        pv.setProdid(request.getProdid());
        pv.setOrdersn(request.getOrdersn());
        
		return  pv;
	}
	
	private void check(AddProdEvaluateRequestBean request) throws BaseException{
		Map<String,String> paramMap = new HashMap<String,String>();
		paramMap.put("orderSn", request.getOrdersn());
		paramMap.put("userId", request.getUserid());
		paramMap.put("prodId", request.getProdid());
		
		List<ProdEvaluate> validateValuate = prodEvaluateDAO.validateEvaluate(paramMap);
		if(!ObjectTools.isNullByObject(validateValuate) && validateValuate.size() > 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该订单商品已经评价");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		if(ObjectTools.isNullByString(request.getProdid())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("商品ID不能为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		if(ObjectTools.isNullByString(request.getProdevaluate())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("商品评价不能为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		if(ObjectTools.isNullByString(request.getStarlevel())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("商品星级不能为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
	}

	@Override
	public GetProdEvaluateResponseBean getProdEvaludate(
			GetProdEvaluateRequestBean request) {
		GetProdEvaluateResponseBean responseBean = new GetProdEvaluateResponseBean();
        
        PageInterceptor.startPage(Integer.parseInt(request.getPage()));
        prodEvaluateDAO.getListByProdId(request.getProdid());
        @SuppressWarnings("unchecked")
		PageInfo<ProdEvaluate> pageInfo = PageInterceptor.endPage();
        
        bulidGetProdEvaluateResponseBean(responseBean, pageInfo);
        
		return responseBean;
	}
	
	private GetProdEvaluateResponseBean bulidGetProdEvaluateResponseBean(GetProdEvaluateResponseBean responseBean,
			 PageInfo<ProdEvaluate> pageInfo){
		
		responseBean.setCurrentpage(pageInfo.getPageIndex().toString());
		responseBean.setPagecount(pageInfo.getPageSize().toString());
		responseBean.setTotalcount(pageInfo.getRecordCount().toString());
		responseBean.setTotalpage(pageInfo.getTotalpage().toString());
		
		List<ProdEvaluateBean> prodevaluatelist = new ArrayList<ProdEvaluateBean>();
		ProdEvaluateBean pvBean = null;
		List<YxjUserProdevaluateReply> prodevaluateReplyList = new ArrayList<YxjUserProdevaluateReply>();
		for(ProdEvaluate pv:pageInfo.getList()){
			prodevaluateReplyList = yxjUserProdevaluateReplyDAO.getValuateReplys(pv.getId());
			pvBean = new ProdEvaluateBean();
			pvBean.setCreatetime(DateUtil.toStr(pv.getCreatetime(), "yyyy-MM-dd HH:mm:ss"));
			pvBean.setStarlevel(pv.getStarlevel().toString());
			pvBean.setTelephone(pv.getTelephone());
			pvBean.setProdevaluate(pv.getProdevaluate());
			if(prodevaluateReplyList.size() > 0){
				if(!ObjectTools.isNullByObject(prodevaluateReplyList.get(0))){
					pvBean.setEvaluatereplay(prodevaluateReplyList.get(0).getReplyContent());
				}
			}else{
				pvBean.setEvaluatereplay("");
			}
			prodevaluatelist.add(pvBean);
		}
		
		responseBean.setProdevaluatelist(prodevaluatelist);
		
		return responseBean;
	}
	
	
	

	
}
