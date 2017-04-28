package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.DeliveryFeeRules;



public interface IDeliveryFeeRulesDAO {

	public List<DeliveryFeeRules> getListByBuserId(String buserId);
	
}
