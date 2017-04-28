package com.youxianji.facade.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.ConfigDeliveryAddressRequestBean;
import com.youxianji.service.IDeliveryAddressService;

@Facade(command="1010",comment="添加收货地址业务处理")
@Scope("prototype")
public class ConfigDeliveryAddressFacade extends AbsFacade{
	
	@Resource
	private IDeliveryAddressService delliveryAddressServcie;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ConfigDeliveryAddressRequestBean requestBean = (ConfigDeliveryAddressRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		//获取设置标记
		String configflag = requestBean.getConfigflag();
		String telephone= requestBean.getTelephone();
		System.out.println("手机号："+telephone);
		
		if("1".equals(configflag)){
			this.delliveryAddressServcie.saveAddress(requestBean);
		}else if("2".equals(configflag)){
			this.delliveryAddressServcie.editAddress(requestBean);
		}else if("3".equals(configflag)){
			this.delliveryAddressServcie.deleteById(requestBean.getDaid());
		}else if("4".equals(configflag)){
			this.delliveryAddressServcie.deleteByUserId(requestBean.getUserid());
		}else if("5".equals(configflag)){
			this.delliveryAddressServcie.setDefaultAddress(requestBean);
		}
		
		return responseParam;
	}

}
