package com.youxianji.facade.system.bean;

import java.util.List;

import com.youxianji.facade.system.bean.jsonbean.ReceiveAddressData;

/**
 * 获取收货地址列表接口返回属性 BEAN
 * @author xyuser
 * command=1009
 *
 */
public class GetDeliveryAddressResponseBean {

	/* 收货地址列表 */
	private List<ReceiveAddressData> addresslist;

	public List<ReceiveAddressData> getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(List<ReceiveAddressData> addresslist) {
		this.addresslist = addresslist;
	}
}
