package com.youxianji.dao;

import com.youxianji.pojo.UserThirdPay;

public interface IUserThirdPayDAO {
	
	    
	public void insert(UserThirdPay userThirdPay);
	public UserThirdPay getUserThirdPayByOrderSn(String ordersn);
	
}
