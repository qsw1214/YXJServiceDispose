package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.DeliveryAddress;


public interface IDeliveryAddressDAO {
	
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
	 * @param deliveryAddress
	 * @return
	 */
	public void insertAddress(DeliveryAddress deliveryAddress);
	
	/**
	 * 修改收货地址
	 * @param deliveryAddress
	 * @return
	 */
	public void updateAddress(DeliveryAddress deliveryAddress);
	
	/**
	 * 根据ID删除收货地址
	 */
	public void deleteById(String daid); 
	
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
	
	public void updateDefaultAddress(String isdefault,String did);
}
