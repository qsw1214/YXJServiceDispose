package com.youxianji.facade.yxjia;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIASettleCartInfoRequestBean;
import com.youxianji.facade.yxjia.bean.JIASettleCartInfoResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIACartDetailBean;
import com.youxianji.service.IJIASettleCartInfoService;

@Facade(command="8003",comment="JIA-结算购物车")
@Scope("prototype")
public class JIASettleCartInfoFacade extends AbsFacade{
	
	@Resource
	private IJIASettleCartInfoService jiasettleCartInfoService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIASettleCartInfoResponseBean responseBean = new JIASettleCartInfoResponseBean();
		JIASettleCartInfoRequestBean requestBean = (JIASettleCartInfoRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        String userId = requestBean.getUserid();
        String deviceNo = requestBean.getPublicBean().getImei();
        String os = requestBean.getPublicBean().getOs();
        List<JIACartDetailBean> detailList = requestBean.getDetaillist();
        //这里需要判断detailList 是否为空
        synchronized (requestBean.getUserid().intern()) {
	        responseBean =  jiasettleCartInfoService.doSettleCartInfo(userId,os,deviceNo, detailList);
        }
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
