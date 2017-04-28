package com.youxianji.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.facade.system.bean.ConfigDeliveryAddressRequestBean;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.service.IDeliveryAddressService;

@Service("deliveryAddressService")
public class DeliveryAddressServiceImpl implements IDeliveryAddressService {

	@Resource
	private IDeliveryAddressDAO deliveryAddressDAO;

	@Override
	public List<DeliveryAddress> getAddressListByUserId(String userId) {
		
		return this.deliveryAddressDAO.getAddressListByUserId(userId);
	}

	@Override
	public DeliveryAddress getAddressById(String daid) {
		
		return this.deliveryAddressDAO.getAddressById(daid);
	}

	@Override
	public void saveAddress(ConfigDeliveryAddressRequestBean requestBean) {
		
		DeliveryAddress address = new DeliveryAddress();
		String daid = UUIDGenerator.getUUID();
		address.setDaid(daid);
		address.setUserid(requestBean.getUserid());
		address.setTelephone(requestBean.getReceivephone());
		address.setReceivename(requestBean.getReceivename());
		address.setBuildingname(requestBean.getBuildingname());
		address.setReceiveaddress(requestBean.getReceiveaddress());
		address.setLongitude(requestBean.getLongitude());
		address.setLatitude(requestBean.getLatitude());
		address.setIsdefault(requestBean.getIsdefault());
		address.setState("1");
		address.setCreatetime(new Date());
		address.setProvince(requestBean.getProvince());
		address.setCity(requestBean.getCity());
		address.setDistrict(requestBean.getDistrict());
		
		if("1".equals(requestBean.getIsdefault())){
			//将其他设为默认的地址修改为非默认
			DeliveryAddress otherAddress = this.getDefaultById(daid,requestBean.getUserid());
			if(!ObjectTools.isNullByObject(otherAddress)){
				otherAddress.setIsdefault("0");
				this.updateAddress(otherAddress);
			}
		}
		
		this.deliveryAddressDAO.insertAddress(address);
	}
	@Override
	public void deleteById(String daid) {
		
		deliveryAddressDAO.deleteById(daid);
	}

	@Override
	public void editAddress(ConfigDeliveryAddressRequestBean requestBean) {
		DeliveryAddress address = this.getAddressById(requestBean.getDaid());
		
		address.setTelephone(requestBean.getReceivephone());
		address.setReceivename(requestBean.getReceivename());
		address.setBuildingname(requestBean.getBuildingname());
		address.setReceiveaddress(requestBean.getReceiveaddress());
		address.setLongitude(requestBean.getLongitude());
		address.setLatitude(requestBean.getLatitude());
		address.setIsdefault(requestBean.getIsdefault());
		address.setCreatetime(new Date());
		address.setProvince(requestBean.getProvince());
		address.setCity(requestBean.getCity());
		address.setDistrict(requestBean.getDistrict());
		
		if("1".equals(requestBean.getIsdefault())){
			//将其他设为默认的地址修改为非默认
			DeliveryAddress otherAddress = this.getDefaultById(requestBean.getDaid(),requestBean.getUserid());
			if(!ObjectTools.isNullByObject(otherAddress)){
				otherAddress.setIsdefault("0");
				this.updateAddress(otherAddress);
			}
		}
		this.deliveryAddressDAO.updateAddress(address);
		
	}

	@Override
	public void insertAddress(DeliveryAddress deliveryAddress) {
		
		
		this.deliveryAddressDAO.insertAddress(deliveryAddress);
	}

	@Override
	public void updateAddress(DeliveryAddress deliveryAddress) {
		
		this.deliveryAddressDAO.updateAddress(deliveryAddress);
	}

	@Override
	public DeliveryAddress getDefaultById(String daid,String userid) {
		
		return this.deliveryAddressDAO.getDefaultById(daid,userid);
	}

	@Override
	public void deleteByUserId(String userid) {
		
		this.deliveryAddressDAO.deleteByUserId(userid);
	}

	@Override
	public void setDefaultAddress(ConfigDeliveryAddressRequestBean requestBean) {
		
		List<DeliveryAddress> deliveryList = deliveryAddressDAO.getAddressListByUserId(requestBean.getUserid());
		if(deliveryList != null){
			for(DeliveryAddress da:deliveryList){
				deliveryAddressDAO.updateDefaultAddress("0", da.getDaid());
			}
		}
		deliveryAddressDAO.updateDefaultAddress("1", requestBean.getDaid());

	}
}
