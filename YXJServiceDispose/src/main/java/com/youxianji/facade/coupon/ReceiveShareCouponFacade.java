package com.youxianji.facade.coupon;

import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.coupon.bean.ReceiveShareCouponRequestBean;
import com.youxianji.facade.coupon.bean.ReceiveShareCouponResponseBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.YxjUserCouponShare;
import com.youxianji.pojo.YxjUserCouponShareDetail;
import com.youxianji.service.IYxjUserShareCouponDetailService;
import com.youxianji.service.IYxjUserShareCouponService;
import com.youxianji.util.DateUtil;

import base.cn.annotation.Facade;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

@Facade(command="h002",comment="领取分享红包")
@Scope("prototype")
public class ReceiveShareCouponFacade extends AbsFacade{
	
	@Resource
	private IYxjUserShareCouponDetailService yxjUserShareCouponDetailService;
	@Resource
	private IYxjUserShareCouponService yxjUserShareCouponService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ReceiveShareCouponRequestBean requestBean = (ReceiveShareCouponRequestBean)baseRequest;
		ReceiveShareCouponResponseBean responseBean = new ReceiveShareCouponResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String shareId = requestBean.getShareid();
		String userId = requestBean.getUserid();
		
		//验证该红包是否已经过期
		YxjUserCouponShare couponShare = yxjUserShareCouponService.validateTimeEndByShareId(shareId);
		if(ObjectTools.isNullByObject(couponShare)){
			responseBean.setResultflag("3");
			responseParam.getParamdata().add(responseBean);
			
			return responseParam;
		}
		
		//同步控制领取红包环节
		synchronized (shareId.intern()) {
			
			//验证该红包是否已经领过
			YxjUserCouponShareDetail validateIsReceived = yxjUserShareCouponDetailService.validateIsReceivedByShareId(shareId, userId);
			if(!ObjectTools.isNullByObject(validateIsReceived)){
				//返回不为空，则返回该用户已经领过红包
				responseBean.setResultflag("1");
				
				VoucherBean ticketdetail = new VoucherBean();
	        	ticketdetail.setDatebegin(DateUtil.toStr(validateIsReceived.getBeginTime(), "yyyy-MM-dd"));
	        	ticketdetail.setDateend(DateUtil.toStr(validateIsReceived.getEndTime(), "yyyy-MM-dd"));
	        	ticketdetail.setDescrib(validateIsReceived.getCouponDesc());
	        	ticketdetail.setTicketid(validateIsReceived.getCouponId());
	        	ticketdetail.setTickettitle(validateIsReceived.getCouponTitle());
	        	ticketdetail.setValue(String.valueOf(validateIsReceived.getCouponAmount()));
	        	
	        	responseBean.setTicketdetail(ticketdetail);
	        	
				responseParam.getParamdata().add(responseBean);
				
				return responseParam;
			}
			
			responseBean = yxjUserShareCouponDetailService.doGetOneCoupon(userId, shareId, responseBean);
			responseParam.getParamdata().add(responseBean);
			
			return responseParam;
		}
	}
	
			
}
