package com.youxianji.facade.trade.bean;


/**
 * 登录验证接口返回属性 BEAN
 * @author xyuser
 * command=1001
 *
 */
public class EditCartInfoResponseBean{
	private String cartquantity;//	购物车总数量	
	private String carttotal;//	String	购物车总金额	
	public String getCartquantity() {
		return cartquantity;
	}
	public void setCartquantity(String cartquantity) {
		this.cartquantity = cartquantity;
	}
	public String getCarttotal() {
		return carttotal;
	}
	public void setCarttotal(String carttotal) {
		this.carttotal = carttotal;
	}

	
	
	
}
