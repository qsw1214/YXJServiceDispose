package com.youxianji.facade.trade;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.trade.bean.SettleCartInfoRequestBean;
import com.youxianji.facade.trade.bean.SettleCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartDetailBean;
import com.youxianji.service.ISettleCartInfoV2Service;

@Facade(command="4010",comment="新结算购物车")
@Scope("prototype")
public class SettleCartInfoV2Facade extends AbsFacade{
	
	@Resource
	private ISettleCartInfoV2Service settleCartInfoV2Service;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		SettleCartInfoResponseBean responseBean = new SettleCartInfoResponseBean();
		SettleCartInfoRequestBean requestBean = (SettleCartInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        String userId = requestBean.getUserid();
        String deviceNo = requestBean.getPublicBean().getImei();
        String os = requestBean.getPublicBean().getOs();
        List<CartDetailBean> detailList = requestBean.getDetaillist();
        //这里需要判断detailList 是否为空
		synchronized (userId.intern()) {

			 responseBean =  settleCartInfoV2Service.doSettleCartInfo(userId,os,deviceNo, detailList,requestBean.getInvitecode());
		
		}
		responseParam.getParamdata().add(responseBean);
		return responseParam;
	}

}
