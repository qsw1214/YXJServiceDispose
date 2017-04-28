package com.youxianji.service;

import java.util.List;

import com.youxianji.facade.system.bean.ConfigDeliveryAddressRequestBean;
import com.youxianji.pojo.DeliveryAddress;

public interface IDeliveryAddressService {

	/**
	 * 根据用户ID获取收货地址信息
	 * @param userId
	 * @return
	 */
	public List<DeliveryAddress> getAddressListByUserId(String userId);
	
	/**
	 * 根据地址ID获取收货地址信息
	 * @param adid
	 * @return
	 */
	public DeliveryAddress getAddressById(String daid);
	
	/**
	 * 保存收货地址
	 * @param baseRequest
	 * 
	 */
	public void saveAddress(ConfigDeliveryAddressRequestBean requestBean);
	
	/**
	 * 编辑收货地址
	 * @param baseRequest
	 * 
	 */
	public void editAddress(ConfigDeliveryAddressRequestBean requestBean);
	
	public void setDefaultAddress(ConfigDeliveryAddressRequestBean requestBean);
	
	/**
	 * 删除收货地址
	 */
	public void deleteById(String daid);
	
	/**
	 * 收货地址插入操作
	 */
	public void insertAddress(DeliveryAddress deliveryAddress);
	
	/**
	 * 收货地址修改操作
	 * @param deliveryAddress
	 */
	public void updateAddress(DeliveryAddress deliveryAddress);
	
	/**
	 * 根据ID查找其他有默认设置的收货地址
	 * @param daid
	 * @return
	 */
	public DeliveryAddress getDefaultById(String daid,String userid);
	
	/**
	 * 根据用户ID删除收货地址
	 * @param userid
	 */
	public void deleteByUserId(String userid);

}
