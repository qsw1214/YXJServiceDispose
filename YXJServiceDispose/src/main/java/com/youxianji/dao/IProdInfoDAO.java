package com.youxianji.dao;

import java.util.List;
import java.util.Map;

import com.youxianji.pojo.ProdInfo;



public interface IProdInfoDAO {
	
	   public ProdInfo getById(String prodId);
	   
	   public List<ProdInfo> getProdInfoList();
	   
	   public List<ProdInfo> getProdInfoListByParamsMap(Map<String,String> paramsMap);
	   
	   public List<ProdInfo> getProdInfoListByType(String type);
	   
	   public List<ProdInfo> getProdListBySearchName(String searchName);
	   
	   /**
	    * 增加商品库存
	    * purchaseQuantity 库存增量
	    */
	   void updateAddStock(String prodid,String purchaseQuantity);
	   /**
	    * 增加商品库存
	    * @param prodid
	    * @param purchaseQuantity 库存减少量
	    */
	   void updateSubStock(String prodid,String purchaseQuantity);
	   
	   List<ProdInfo> getProdListByTags(String tagId);
	   
   
}
