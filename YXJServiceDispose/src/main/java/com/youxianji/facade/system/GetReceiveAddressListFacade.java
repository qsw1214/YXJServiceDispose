package com.youxianji.facade.system;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.GetDeliveryAddressRequestBean;
import com.youxianji.facade.system.bean.GetDeliveryAddressResponseBean;
import com.youxianji.facade.system.bean.jsonbean.ReceiveAddressData;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.service.IDeliveryAddressService;

@Facade(command="1009",comment="获取收货地址业务处理")
@Scope("prototype")
public class GetReceiveAddressListFacade extends AbsFacade{
	
	@Resource
	private IDeliveryAddressService delliveryAddressServcie;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetDeliveryAddressRequestBean requestBean = (GetDeliveryAddressRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();

		GetDeliveryAddressResponseBean responseBean = new GetDeliveryAddressResponseBean();
		
		//获取用户ID
		String userId = requestBean.getUserid();
		
		List<DeliveryAddress> initAddressList = this.delliveryAddressServcie.getAddressListByUserId(userId);
		
		responseBean.setAddresslist(this.getAddressData(initAddressList));
		
		responseParam.getParamdata().add(responseBean);
		
        return responseParam; 
	}

	
	private List<ReceiveAddressData> getAddressData(List<DeliveryAddress> initAddressList){
		List<ReceiveAddressData> addressList = new ArrayList<ReceiveAddressData>();
		for(DeliveryAddress deliveryAddress:initAddressList){
			ReceiveAddressData addressData = new ReceiveAddressData();
			addressData.setDaid(deliveryAddress.getDaid());
			addressData.setTelephone(deliveryAddress.getTelephone());
			addressData.setReceivename(deliveryAddress.getReceivename());
			addressData.setBuildingname(deliveryAddress.getBuildingname());
			addressData.setReceiveaddress(deliveryAddress.getReceiveaddress());
			addressData.setLongitude(deliveryAddress.getLongitude());
			addressData.setLatitude(deliveryAddress.getLatitude());
			addressData.setIsdefault(deliveryAddress.getIsdefault());
			addressData.setProvince(deliveryAddress.getProvince());
			addressData.setCity(deliveryAddress.getCity());
			addressData.setDistrict(deliveryAddress.getDistrict());
			
			addressList.add(addressData);
		}
		
		return addressList;
	}
}
