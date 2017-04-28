package com.youxianji.service.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IYxjUserPrepaycardStockDAO;
import com.youxianji.pojo.YxjUserPrepaycardStock;
import com.youxianji.service.IYxjUserPrepaycardStockService;
import com.youxianji.util.bill.BillTransact;
import com.youxianji.util.bill.parambean.CUserParam;

@Service("yxjUserPrepaycardStockService")
public class YxjUserPrepaycardStockServiceImpl implements IYxjUserPrepaycardStockService{
	
	@Resource
	private IYxjUserPrepaycardStockDAO yxjUserPrepaycardStockDAO;

	@Override
	public YxjUserPrepaycardStock getByCardCode(String cardCode) {

		
		return yxjUserPrepaycardStockDAO.getByCardCode(cardCode);
	}

	@Override
	public void doChargeForUser(YxjUserPrepaycardStock stock, String userId) {

		   CUserParam cuserParam = new CUserParam();

			cuserParam.setUserId(userId);
			cuserParam.setOperatesn(stock.getCardNo()+stock.getCardCode());
			cuserParam.setOperatemoney(new BigDecimal(stock.getFaceValue().toString()));
			cuserParam.setAmountbalance(null);
			cuserParam.setBillsflag("0");
			cuserParam.setOperatedesc("实体卡充值");
			cuserParam.setRemark("实体卡充值");
			
			BillTransact.mobileUserTransact(cuserParam);
			
			yxjUserPrepaycardStockDAO.update(stock.getCardCode(), userId);
			
		
	}

	@Override
	public YxjUserPrepaycardStock getByNoRepeat(String userId,String repeatFlag) {
		// TODO Auto-generated method stub
		return yxjUserPrepaycardStockDAO.getByNoRepeat(userId,repeatFlag);
	}

	

}
