package com.youxianji.pojo;

import java.math.BigDecimal;


public class YxjBuserCartInfo {

	private String cartId;
	private UserInfo userInfo;
	private ProdInfo prodInfo;
	private String prodName;
	private Integer prodQuantity;
	private BigDecimal prodTotalPrice;
	public String getCartId() {
		return cartId;
	}
	public void setCartId(String cartId) {
		this.cartId = cartId;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public ProdInfo getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(ProdInfo prodInfo) {
		this.prodInfo = prodInfo;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public Integer getProdQuantity() {
		return prodQuantity;
	}
	public void setProdQuantity(Integer prodQuantity) {
		this.prodQuantity = prodQuantity;
	}
	public BigDecimal getProdTotalPrice() {
		return prodTotalPrice;
	}
	public void setProdTotalPrice(BigDecimal prodTotalPrice) {
		this.prodTotalPrice = prodTotalPrice;
	}
	
	
	
	
	
	
}
