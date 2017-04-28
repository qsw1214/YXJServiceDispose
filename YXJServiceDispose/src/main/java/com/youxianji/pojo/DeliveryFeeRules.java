package com.youxianji.pojo;

import java.math.BigDecimal;

public class DeliveryFeeRules {

      private String id;
      private BigDecimal sinceprice;
      private BigDecimal deliverfee;
      private BigDecimal limitweight;
      private BusinessUserInfo businessUser;
      
      
      
      
	public BigDecimal getLimitweight() {
		return limitweight;
	}
	public void setLimitweight(BigDecimal limitweight) {
		this.limitweight = limitweight;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getSinceprice() {
		return sinceprice;
	}
	public void setSinceprice(BigDecimal sinceprice) {
		this.sinceprice = sinceprice;
	}
	public BigDecimal getDeliverfee() {
		return deliverfee;
	}
	public void setDeliverfee(BigDecimal deliverfee) {
		this.deliverfee = deliverfee;
	}
	public BusinessUserInfo getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(BusinessUserInfo businessUser) {
		this.businessUser = businessUser;
	}
      
}
	
