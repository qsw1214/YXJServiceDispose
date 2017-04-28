package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.ICartInfoDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.facade.trade.bean.EditCartInfoRequestBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.CartInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ICartInfoService;
import com.youxianji.web.util.ErrorEnum;

@Service("cartInfoService")
public class CartInfoServiceImpl implements ICartInfoService {
	
	Logger logger = Logger.getLogger(CartInfoServiceImpl.class);

	@Resource
	private ICartInfoDAO cartInfoDAO;
	@Resource
	private IProdInfoDAO prodInfoDAO;
	@Resource
    private IUserInfoDAO userInfoDAO;
	@Resource
	private IActivityProdInfoDAO activityProdInfoDAO;
	


	@Override
	public List<CartInfo> getListCartInfoByUserId(String userId) throws BaseException {

		return cartInfoDAO.getListCartInfoByUserId(userId);
	}

	@Override
	public void editCartInfo(EditCartInfoRequestBean requestBean)
			throws BaseException {
		UserInfo userInfo = userInfoDAO.getById(requestBean.getUserid());
		ProdInfo prodInfo = prodInfoDAO.getById(requestBean.getProdid());
		String configFlag = requestBean.getConfigflag();//1.添加2.修改 3.删除 4.全部删除
		String quantity = requestBean.getQuantity();//每次变化数 而不是总数
		logger.info("加入购物车的产品ID:"+requestBean.getProdid());
		//必要的校验
		if("1".equals(configFlag)){
			check(userInfo,prodInfo,quantity);
		}
		//构建CartInfo对象
		CartInfo cartInfo = buildCartInfo(userInfo,prodInfo,quantity);
	    //编辑购物车
     
        doEditCartInfo(cartInfo,configFlag,prodInfo,userInfo);
		
	}
	
	private void check(UserInfo userInfo,ProdInfo prodInfo,String quantity) throws BaseException{
		/*if(Integer.valueOf(quantity) != 1){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("购买数量不正确");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}*/
		if(prodInfo == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该产品不存在");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		if(!"1".equals(userInfo.getIsfreshman())){
			if("1".equals(prodInfo.getIsfreshman())){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("您不是新人,不能购买该类商品");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}else{
			CartInfo freshCart = cartInfoDAO.queryIsFreshProdInfo(userInfo.getUserId());
			if(freshCart != null && "1".equals(prodInfo.getIsfreshman())){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("新人专享商品只能购买一个");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			
		}
		//如果是一分购商品
	    ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
	    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
	    	
	    	
	    	CartInfo checkActivity = cartInfoDAO.getByProdIdAndUserId(prodInfo.getProdid(),userInfo.getUserId());
	    	if(checkActivity != null && checkActivity.getProdQuantity() >= activeInfo.getLimitcount()){
	    		ErrorEnum.valueOf("FAIL_5555").changetMessage("您只能购买"+activeInfo.getLimitcount()+"个活动商品");
				throw new BaseException(ErrorEnum.FAIL_5555);
	    	}
	    	
	    	if("1".equals(activeInfo.getIsonecent())){
	    		//只有购物车为空的时候才能够添加
		    	String cartCount = cartInfoDAO.getCartInfoCount(userInfo.getUserId());
		    	logger.info("cartCount:"+cartCount);
		    	if(cartCount != null && Integer.valueOf(cartCount) > 0){
		    		ErrorEnum.valueOf("FAIL_5555").changetMessage("一分购商品只能单独购买一个");
					throw new BaseException(ErrorEnum.FAIL_5555);
		    	}
		    	//判断是否购买1分够产品
		    	if("1".equals(userInfo.getIsOnecent())){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("您已购买过一分购产品，不能再次购买");
					throw new BaseException(ErrorEnum.FAIL_5555);
				}
	    	}
	    	
	    	
	    	
	    	
	    }
		
	    CartInfo onecentCart =cartInfoDAO.queryIsOnecentProdInfo(userInfo.getUserId());
	    logger.info("onecentCart:"+onecentCart);
    	if(onecentCart != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("一分购商品只能单独购买");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
    	
	}
	
	
	private void doEditCartInfo(CartInfo cartInfo,String configFlag,ProdInfo prod,UserInfo user) throws BaseException {
		CartInfo queryCF = cartInfoDAO.getByProdIdAndUserId(prod.getProdid(),user.getUserId());
		switch(configFlag){
	 	    case "1":
	 	    	if(queryCF == null){
	 	    		//购物车中无该商品
	 	    		cartInfoDAO.save(cartInfo);
	 	    	}else{
	 	    		//购物车中有该商品
	 	    		cartInfo.setCartId(queryCF.getCartId());
	 	    		cartInfo.setProdQuantity(queryCF.getProdQuantity()+cartInfo.getProdQuantity());
	 	    		cartInfo.setProdTotalPrice(queryCF.getProdTotalPrice().add(cartInfo.getProdTotalPrice()));
	 	    		cartInfoDAO.update(cartInfo);
	 	    	}
	 	    	break;
	 	    case "2":
	 	    	if(queryCF == null){
	 	    		//购物车中无该商品
	 	    		cartInfoDAO.save(cartInfo);
	 	    		break;
	 	    	}
	 	    	if(queryCF.getProdQuantity() <= 1){
	 	    		cartInfoDAO.deleteByCartId(queryCF.getCartId());
		 	    	break;
	 	    	}
	 	    	//计算总数量和总金额
	 	    	cartInfo.setCartId(queryCF.getCartId());
	 	    	cartInfo.setProdQuantity(queryCF.getProdQuantity()-cartInfo.getProdQuantity());
		    		cartInfo.setProdTotalPrice(queryCF.getProdTotalPrice().subtract(cartInfo.getProdTotalPrice()));
	 	    	cartInfoDAO.update(cartInfo);
	 	    	break;
	 	    case "3":
	 	    	if(queryCF != null){
	 	    		cartInfoDAO.deleteByCartId(queryCF.getCartId());
	 	    	}
	 	    	break;
	 	    case "4":
	 	    	if(queryCF != null){
		 	    	cartInfoDAO.deleteByUserId(user.getUserId());
		 	   	}
	 	    	break;
		}
		
	}
	private CartInfo buildCartInfo(UserInfo userInfo,ProdInfo prodInfo,String quantity){
		CartInfo cartInfo = new CartInfo();
		if(prodInfo != null){
			ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
			    //如果是活动商品
		    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
			    cartInfo.setProdPrice(activeInfo.getActivePrice());			   
		    }else if("1".equals(prodInfo.getIsfreshman())){
		    	//如果是新人专享商品
		    	cartInfo.setProdPrice(prodInfo.getFreshmanprice());
		    }else{
		    	//如果是普通商品就按会员价（初期）
		    	cartInfo.setProdPrice(prodInfo.getSellPrice());
		    }
			cartInfo.setProdInfo(prodInfo);
			cartInfo.setImageUrl(prodInfo.getImageUrl());
			cartInfo.setPreSellTag(prodInfo.getPreSellTag());
			cartInfo.setPreSellTime(prodInfo.getPreSellTime());
			cartInfo.setProdName(prodInfo.getProdname());
			cartInfo.setProdOriginalPrice(prodInfo.getValuePrice());
			cartInfo.setProdUnit(prodInfo.getProdUnit());
			cartInfo.setProdQuantity(new Integer(quantity));
			cartInfo.setProdTotalPrice(new BigDecimal(quantity).multiply(prodInfo.getSellPrice()));
		}
		cartInfo.setCartId(UUIDGenerator.getUUID());
		cartInfo.setUserInfo(userInfo);
		return cartInfo;
	}
	
	
	/**
	 * 获取购物车总数
	 */
	@Override
	public String getCartInfoCount(String userId)throws BaseException {
		
		return cartInfoDAO.getCartInfoCount(userId);
	}
	
	
	public BigDecimal getActualPrice(ProdInfo prodInfo){
		ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
	    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
		    return activeInfo.getActivePrice();			   
	    }else if("1".equals(prodInfo.getIsfreshman())){
	    	//如果是新人专享商品
	    	return prodInfo.getFreshmanprice();
	    }else{
	    	//如果是普通商品就按会员价（初期）
	    	return prodInfo.getSellPrice();
	    }
	}
	
	public BigDecimal getCartActualTotalPrice(List<CartInfo> cartInfoList){
		
		return getCartTotalPrice(cartInfoList,"A");
	}
	
    public BigDecimal getCartMemberTotalPrice(List<CartInfo> cartInfoList){
		
		return getCartTotalPrice(cartInfoList,"M");
	}
	
	private BigDecimal getCartTotalPrice(List<CartInfo> cartInfoList,String flag){
		BigDecimal totalPrice = new BigDecimal("0");
		for(CartInfo ci:cartInfoList){
			  ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(ci.getProdInfo().getProdid());
		    if(activeInfo != null && "1".equals(ci.getProdInfo().getIsactivity())){
			    totalPrice = totalPrice.add(activeInfo.getActivePrice());			   
		    }else if("1".equals(ci.getProdInfo().getIsfreshman())){
		    	totalPrice = totalPrice.add(ci.getProdInfo().getFreshmanprice());	
		    }else{
		    	totalPrice = totalPrice.add(
		    			"A".equals(flag)?ci.getProdInfo().getValuePrice():
		    				"M".equals(flag)?ci.getProdInfo().getSellPrice():null);
		    }
		}
		
		return totalPrice;
	}
	
	
}
