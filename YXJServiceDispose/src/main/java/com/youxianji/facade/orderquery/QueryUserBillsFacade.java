package com.youxianji.facade.orderquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.mybatis.util.PageInfo;

import com.youxianji.facade.orderquery.bean.QueryUserBillsRequestBean;
import com.youxianji.facade.orderquery.bean.QueryUserBillsResponseBean;
import com.youxianji.facade.orderquery.bean.jsonbean.PageBillsData;
import com.youxianji.pojo.UserBills;
import com.youxianji.service.IUserBillsService;
import com.youxianji.util.DateUtil;

@Facade(command="2003",comment="查询用户账务业务处理")
@Scope("prototype")
public class QueryUserBillsFacade extends AbsFacade{
	
	@Resource
	private IUserBillsService userBillsService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		QueryUserBillsRequestBean requestBean = (QueryUserBillsRequestBean)baseRequest;
		QueryUserBillsResponseBean responseBean = new QueryUserBillsResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		Map<String,String> hashMap = new HashMap<String,String>();
		hashMap.put("page", requestBean.getPage());
		hashMap.put("userid", requestBean.getUserid());
		
		PageInfo<UserBills> pageOrder = userBillsService.getPageBillsByUserId(hashMap);
		buildResultList(pageOrder,responseBean);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	private void buildResultList(PageInfo<UserBills> page,QueryUserBillsResponseBean responseBean){
		responseBean.setCurrentpage(page.getPageIndex().toString());
		responseBean.setPagecount(page.getPageSize().toString());
		responseBean.setTotalcount(page.getRecordCount().toString());
		responseBean.setTotalpage(page.getTotalpage().toString());
		
		List<PageBillsData> billlist = new ArrayList<PageBillsData>();
		List<UserBills> userBillList = page.getList();
		int size = page.getList().size();
		PageBillsData billsData = null;
		UserBills userBill = null;
		for(int i=0;i<size;i++){
			billsData = new PageBillsData();
			userBill = userBillList.get(i);
			bulidResultData(billsData,userBill);
			billlist.add(billsData);
		}
		
		responseBean.setBilllist(billlist);
	}
	
	private void bulidResultData(PageBillsData billsData,UserBills userBill){
		billsData.setOrdersn(userBill.getOperatesn());
		billsData.setOrdertime(DateUtil.toStr(userBill.getOperatetime(), "yyyy-MM-dd HH:mm:ss"));
		billsData.setBilltype(userBill.getBillsflag());
		billsData.setMoney(userBill.getOperatemoney().toString());
		billsData.setBalance(userBill.getAmountbalance().toString());
		billsData.setOperateflag(userBill.getOperatedesc());
	}

}
