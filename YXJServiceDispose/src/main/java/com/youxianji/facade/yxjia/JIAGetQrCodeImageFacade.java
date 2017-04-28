package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.yxjia.bean.JIAGetQrCodeImageRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetQrCodeImageResponseBean;
import com.youxianji.pojo.YxjBuserShopInfo;
import com.youxianji.service.IYxjBuserShopInfoService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="8008",comment="JIA-获取商家支付二维码")
@Scope("prototype")
public class JIAGetQrCodeImageFacade extends AbsFacade{
	
	@Resource
	private IYxjBuserShopInfoService yxjBuserShopInfoService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetQrCodeImageResponseBean responseBean = new JIAGetQrCodeImageResponseBean();
		JIAGetQrCodeImageRequestBean requestBean = (JIAGetQrCodeImageRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
       
		String userId = requestBean.getUserid();
    
		YxjBuserShopInfo shopInfo = yxjBuserShopInfoService.getShopDetailByUserId(userId);
		if(shopInfo == null){
			ErrorEnum.FAIL_5555.changetMessage("该用户不存在店铺");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		responseBean.setShopname(shopInfo.getShopName());
		responseBean.setQrcodeurl(shopInfo.getQrCodeUrl());
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
