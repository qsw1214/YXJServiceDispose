package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;


public class CartInfo {

	private String cartId;
	private UserInfo userInfo;
	private ProdInfo prodInfo;
	private String prodName;
	private String imageUrl;
	private BigDecimal prodOriginalPrice;//商品原价
	private BigDecimal prodPrice;//商品实际售价
	private String preSellTag;
	private String prodUnit;
	private Date preSellTime;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public BigDecimal getProdOriginalPrice() {
		return prodOriginalPrice;
	}
	public void setProdOriginalPrice(BigDecimal prodOriginalPrice) {
		this.prodOriginalPrice = prodOriginalPrice;
	}
	public BigDecimal getProdPrice() {
		return prodPrice;
	}
	public void setProdPrice(BigDecimal prodPrice) {
		this.prodPrice = prodPrice;
	}
	public String getPreSellTag() {
		return preSellTag;
	}
	public void setPreSellTag(String preSellTag) {
		this.preSellTag = preSellTag;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public Date getPreSellTime() {
		return preSellTime;
	}
	public void setPreSellTime(Date preSellTime) {
		this.preSellTime = preSellTime;
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
