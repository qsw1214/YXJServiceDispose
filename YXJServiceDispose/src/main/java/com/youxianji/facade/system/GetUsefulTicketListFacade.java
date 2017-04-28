package com.youxianji.facade.system;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.GetUsefulTicketListRequestBean;
import com.youxianji.facade.system.bean.GetUsefulTicketListResponseBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.CouponRuleInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.util.DateUtil;

@Facade(command="1013",comment="获取可使用代金券列表")	//自定义标签
@Scope("prototype")	//标签
public class GetUsefulTicketListFacade extends AbsFacade{
	

	@Resource
	private ICouponUseInfoService couponUseInfoService;
	
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetUsefulTicketListResponseBean responseBean = new GetUsefulTicketListResponseBean();
		GetUsefulTicketListRequestBean requestBean = (GetUsefulTicketListRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String userId = requestBean.getUserid();
		
		//查询该用户的代金券列表
		List<VoucherBean> ticketlist = new ArrayList<VoucherBean>();
		
		couponUseInfoService.updateCancel(userId);
		List<CouponUseInfo> couponUseList = couponUseInfoService.getUsefulCouponList(userId);
		if(couponUseList != null && couponUseList.size() > 0){
			for(CouponUseInfo couponUse:couponUseList){
				VoucherBean ticketdetail = new VoucherBean();
				CouponRuleInfo ruleInfo = couponUse.getCouponRuleInfo();
				ticketdetail.setDatebegin(DateUtil.toStr(couponUse.getUseBeginTime(), "yyyy-MM-dd"));
				ticketdetail.setDateend(DateUtil.toStr(couponUse.getUseEndTime(), "yyyy-MM-dd"));
				ticketdetail.setDescrib(ruleInfo.getCouponDesc());
				ticketdetail.setTicketid(couponUse.getCuid());
				ticketdetail.setTickettitle(couponUse.getCoupontitle());
				ticketdetail.setValue(couponUse.getCouponAmount().toString());
				ticketlist.add(ticketdetail);
			}
			
		}
		
		responseBean.setUseticketlist(ticketlist);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}



}
