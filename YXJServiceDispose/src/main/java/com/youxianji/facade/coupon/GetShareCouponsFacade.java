package com.youxianji.facade.coupon;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.coupon.bean.GetShareCouponsRequestBean;
import com.youxianji.facade.coupon.bean.GetShareCouponsResponseBean;
import com.youxianji.pojo.YxjUserCouponShare;
import com.youxianji.service.IYxjUserShareCouponService;

import base.cn.annotation.Facade;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

@Facade(command="h001",comment="获取分享红包记录")
@Scope("prototype")
public class GetShareCouponsFacade extends AbsFacade{
	
	@Resource
	private IYxjUserShareCouponService yxjUserShareCouponService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetShareCouponsRequestBean requestBean = (GetShareCouponsRequestBean)baseRequest;
		GetShareCouponsResponseBean responseBean = new GetShareCouponsResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String baseOrderSn = requestBean.getBaseordersn();
		YxjUserCouponShare shareCoupon = yxjUserShareCouponService.findCouponShareByOrdersn(baseOrderSn);
		
		if(ObjectTools.isNullByObject(shareCoupon)){
			//如果分享红包查询为空，则返回红包不存在
			responseBean.setExistsflag("0");
		}else{
			//如果分享红包查询不为空，则返回红包存在，并返回红包ID
			responseBean.setExistsflag("1");
			responseBean.setShareid(shareCoupon.getShareId());
			responseBean.setTotalMoney(shareCoupon.getTotalMoney().toString());
		}
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
