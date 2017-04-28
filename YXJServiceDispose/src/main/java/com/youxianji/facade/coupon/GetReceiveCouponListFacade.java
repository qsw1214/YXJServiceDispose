package com.youxianji.facade.coupon;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.coupon.bean.GetReceiveCouponListRequestBean;
import com.youxianji.facade.coupon.bean.GetReceiveCouponListResponseBean;
import com.youxianji.facade.coupon.bean.json.ReceiveCouponBean;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserCouponShareDetail;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserShareCouponDetailService;
import com.youxianji.util.Base64Util;
import com.youxianji.util.DateUtil;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

@Facade(command="h003",comment="获取好友领取红包信息")
@Scope("prototype")
public class GetReceiveCouponListFacade extends AbsFacade{
	
	@Resource
	private IYxjUserShareCouponDetailService yxjUserShareCouponDetailService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetReceiveCouponListRequestBean requestBean = (GetReceiveCouponListRequestBean)baseRequest;
		GetReceiveCouponListResponseBean responseBean = new GetReceiveCouponListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String shareId = requestBean.getShareid();
		List<YxjUserCouponShareDetail> shareDetailList = yxjUserShareCouponDetailService.findCouponShareDetailByShareId(shareId);
		
		List<ReceiveCouponBean> receiveCouponList = new ArrayList<ReceiveCouponBean>();
		List<ReceiveCouponBean> tempCouponList = new ArrayList<ReceiveCouponBean>();
		BigDecimal maxAmount = new BigDecimal(0);
		for(YxjUserCouponShareDetail shareDetail : shareDetailList){
			ReceiveCouponBean receiveBean = new ReceiveCouponBean();
			receiveBean.setUserid(shareDetail.getReceiveUser().getUserId());
				//获取发起砍价人头像和昵称
				WeChatUserInfo weChatUserInfo = weChatUserInfoService.getWeChatUserInfoByUserId(shareDetail.getReceiveUser().getUserId());
				if(weChatUserInfo != null){
					receiveBean.setHeadimgurl(weChatUserInfo.getHeadimgurl());
					String regExp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";  
			        Pattern p = Pattern.compile(regExp);  
			        if(weChatUserInfo.getNickname() != null){
			        	if(p.matcher(weChatUserInfo.getNickname()).matches()){
				        	Base64Util base64Util = new Base64Util();
				        	receiveBean.setNickname(base64Util.Decode(weChatUserInfo.getNickname()));
				        }else{
				        	receiveBean.setNickname(weChatUserInfo.getNickname());
				        }
			        }else{
			        	receiveBean.setNickname("");
			        }
				}
			receiveBean.setReceivetime(DateUtil.toStr(shareDetail.getReceiveTime()));
			receiveBean.setReceivemoney(String.valueOf(shareDetail.getCouponAmount().intValue()));
			//如果当前面值大于最大面值，则替换最大面值
			if(shareDetail.getCouponAmount().compareTo(maxAmount)==1){
				maxAmount = shareDetail.getCouponAmount();
			}
			receiveBean.setBestlucky("0");
			
			tempCouponList.add(receiveBean);
		}
		
		//设置手气最佳的领红包人
		boolean luckFlag = false;
		for(ReceiveCouponBean receiveCouponBean : tempCouponList){
			
			if(!luckFlag && new BigDecimal(receiveCouponBean.getReceivemoney()).compareTo(maxAmount)==0){
				receiveCouponBean.setBestlucky("1");
				luckFlag = true;
			}	
			
			receiveCouponList.add(receiveCouponBean);
		}
		
		
		responseBean.setReceivecouponlist(receiveCouponList);
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
