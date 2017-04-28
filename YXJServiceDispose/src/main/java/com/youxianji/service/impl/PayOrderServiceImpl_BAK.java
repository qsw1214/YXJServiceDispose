package com.youxianji.service.impl;

import org.springframework.stereotype.Service;

//@Service("payOrderService")
/*public class PayOrderServiceImpl_BAK {
	@Resource
	private IDeliveryAddressDAO deliveryAddressDAO;
	@Resource
	private IOrderInfoDAO orderInfoDAO;
	@Resource
	private ICouponUseInfoDAO couponUseInfoDAO;
    @Resource
    private IWeChatUserInfoDAO weChatUserInfoDAO;
    @Resource
    private ICartInfoDAO cartInfoDAO;
    @Resource
    private IUserInfoDAO userInfoDAO;
    @Resource
	private IUserThirdPayDAO userThirdPayDAO;
    @Resource
   	private ISubStoreInfoDAO subStoreInfoDAO;
    @Resource
   	private IOrderprocessinfoDAO orderprocessinfoDAO;
    @Resource
    private IOrderDetailDAO orderDetailDAO;
    @Resource
    private IProdInfoDAO prodInfoDAO;
    @Resource
    private IDeliveryFeeRulesDAO deliveryFeeRulesDAO;
    @Resource
    private IDeductAmtRulesDAO deductAmtRulesDAO;
    @Resource 
    private ISettleCartInfoService settleCartInfoService;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IYxjBuserCartInfoDAO yxjBuserCardInfoDAO;

	@Override
	public void doCommitOrder(CommitOrderRequestBean requestBean)
			throws BaseException {
		String orderSn = requestBean.getOrdersn();
        String addressId = requestBean.getAddressid();
        String couponid = requestBean.getCouponid();
        String orderRemark = requestBean.getOrderremark();
        String distributionInterval = requestBean.getDistributiontime();
        UserInfo user = userInfoDAO.getById(requestBean.getUserid());
        if(ObjectTools.isNullByString(distributionInterval)){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("请选择配送时间段");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        CouponUseInfo couponUseInfo = couponUseInfoDAO.getUsedCoupon(couponid);
        //查询获取收货地址
        DeliveryAddress address = deliveryAddressDAO.getAddressById(addressId);
        //查询获取订单
        OrderInfo order = orderInfoDAO.getByOrderSn(orderSn);
        if(address == null && "1".equals(order.getPayflag()) && !"8004".equals(requestBean.getPublicBean().getCommand())){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("配送商品请选择收货地址");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }else if(address != null && "2".equals(order.getPayflag())){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("自提商品不能选择收货地址");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        //计算实际支付金额
        BigDecimal actuAmt = order.getSellprice();
        //如果使用代金券
        if(couponUseInfo != null && "1".equals(order.getCouponflag())){
        	//同步控制修改代金券状态为“使用中”
        	// synchronized (couponid.intern()) {
        		 couponUseInfo.setOrdersn(orderSn);
            	 couponUseInfo.setState("2");
                 couponUseInfoDAO.update(couponUseInfo);
			//}
        	 
        	//重新设置代金券金额及订单实际支付金额
        	BigDecimal tempCouponAmt = order.getCouponamt();
        	BigDecimal couponAmt = new BigDecimal(couponUseInfo.getCouponAmount());
        	order.setCouponamt(couponAmt);
        	order.setSellprice(actuAmt.add(tempCouponAmt).subtract(couponAmt));
        }
        //通用校验
        WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(requestBean.getUserid());
        super.setCartInfoDAO(cartInfoDAO);
		super.setOrderDetailDAO(orderDetailDAO);
        super.doCommitNecessaryCheck(wechatUser, order, user);

        if(actuAmt.compareTo(new BigDecimal("0")) <= 0){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("订单金额小于等于0");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }
        if(Constants.ORDER_NO_PAY.equals(order.getOrderstate())){
        	ErrorEnum.valueOf("FAIL_5555").changetMessage("订单重复提交");
        	throw new BaseException(ErrorEnum.FAIL_5555);
        }

        order.setDistributionInterval(distributionInterval);
        if(address != null){
        	 order.setReceiveaddress(address.getReceiveaddress());
             order.setReceivename(address.getReceivename());
             order.setReceivetelephone(address.getTelephone());
             order.setReceivebuilding(address.getBuildingname());
             order.setReceivelatitude(address.getLatitude());
             order.setReceivelongitude(address.getLongitude());
             //计算前置仓
             SubStoreInfo storeInfo = findListInArea(address.getLatitude(),address.getLongitude());
             order.setStore( storeInfo == null?calSubStore(address.getLongitude(),address.getLatitude()):storeInfo);
        }
       
        order.setRemark(orderRemark);
        order.setOrderstate(Constants.ORDER_NO_PAY);
    	int cargocode = (int)(Math.random()*(9999-1000+1))+1000;
        order.setCargocode(String.valueOf(cargocode));
        order.setPaytype(requestBean.getPaytype());
        orderInfoDAO.update(order);
        
      
	    
	    this.disposeStock(order,requestBean.getChannel());
	    
	    //记录订单流程
	    Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
	    orderprocessinfo.setOrdersn(order.getOrdersn());
	    orderprocessinfo.setProcessdesc("订单提交成功");
	    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
	    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    orderprocessinfo.setProcessremark("订单号:"+order.getOrdersn());
	    orderprocessinfoDAO.insert(orderprocessinfo);
		
	}
	
	private void disposeStock(OrderInfo order,String channel) throws BaseException{
		 List<OrderDetail> orderDetailList = orderDetailDAO.getDetailListByOrderSn(order.getOrdersn());
	     for(OrderDetail od:orderDetailList){
	    	 
	    	ProdInfoLockManager prodInfoLockManager = ProdInfoLockManager.getInstance();
	 		Lock lock = prodInfoLockManager.getProdInfoLock(od.getProd().getProdid());
	 		lock.lock();
	 		try{
		    	 //判断库存
		    	 if(od.getProd().getProdstock() != null && od.getProd().getProdstock() != -9999 ){
		    		 if(od.getProd().getProdstock() < od.getQuantity()){
			    		 ErrorEnum.valueOf("FAIL_5555").changetMessage("商品"+od.getProd().getProdname()+"库存不足");
						 throw new BaseException(ErrorEnum.FAIL_5555);
			    	 }
		    		 
		    		 //更新商品库存
			    	 prodInfoDAO.updateSubStock(od.getProd().getProdid(), String.valueOf(od.getQuantity()));
		    	 }
	 			
	 		}finally{
	 			lock.unlock();
	 		}
		 	
	 		if("B".equals(channel)){
	 			//清除优鲜季商家端购物车
	 			yxjBuserCardInfoDAO.deleteByProdId(od.getProd().getProdid(),order.getUserInfo().getUserId());
	 		}else{
		 		//清除优鲜季商城购物车
		 	    cartInfoDAO.deleteByProdId(od.getProd().getProdid(),order.getUserInfo().getUserId());
	 		}
	    	 
	    	 
	     }
	}
	
	private SubStoreInfo calSubStore(String latitude,String longitude) throws BaseException{
		List<SubStoreInfo> storeList = subStoreInfoDAO.findList();
		if(storeList == null || storeList.size() <= 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("不存在前置仓");
        	throw new BaseException(ErrorEnum.FAIL_5555);
		}
		SubStoreInfo minStore = storeList.get(0);
		for(int i=1;i<storeList.size();i++){
		    minStore = getMinDistance(latitude,longitude,storeList.get(i),minStore);
		}
		return minStore;
	}
	
	
	private SubStoreInfo findListInArea(String latitude,String longitude) throws BaseException{
		List<SubStoreInfo> storeList = subStoreInfoDAO.findListInArea(latitude, longitude);
		if(storeList != null && storeList.size() > 0){
			return storeList.get(0);
		}
		return null;
	}
	
	
	private SubStoreInfo getMinDistance(String lng1Str, String lat1Str
			, SubStoreInfo store,SubStoreInfo minStore){
		
		if(CalculateDistance.getDistance(lng1Str, lat1Str
				,store.getLongitude(),store.getLatitude()) 
				<
				CalculateDistance.getDistance(lng1Str, lat1Str
						,minStore.getLongitude(),minStore.getLatitude()) ){
			return store;
		}else{
			return minStore;
		}
	}

	@Override
	public Map<String,String> doPayOrder(String orderSn,String payType,String userId,String payPass,String imei,String ip)
			throws BaseException {
		 Map<String,String>  retMap = new HashMap<String,String>();
		
		 WeChatUserInfo wechatUser = weChatUserInfoDAO.getWeChatUserInfoByUserId(userId);
		 OrderInfo order = orderInfoDAO.getByOrderSn(orderSn);
		 UserInfo user = userInfoDAO.getById(userId);
		 
		 //做必要的校验
		 super.setCartInfoDAO(cartInfoDAO);
		 super.setOrderDetailDAO(orderDetailDAO);
		 super.doPayNecessaryCheck(wechatUser,order, user, payPass);
		 
		 //记录第三方订单
		 recordOrder(order, user);
		 
		 switch(order.getPaytype()){
		    case "1":
		    	WechatPayOrder wepay = new WechatPayOrder();
		    	retMap = wepay.doThirdPayDisposeOrder(order,wechatUser.getOpenid(), imei, ip);
		    	orderInfoDAO.update(order);
		    	break;
		    case "2":
		    	retMap = new AliPayOrder().doThirdPayDisposeOrder(order, null,imei, ip);
		    	orderInfoDAO.update(order);
		    	break;
		    case "3":
		    	payFromUserAccount(order, user, payPass);
		    	break;
		    case "4":
		    	WechatAppPayOrder appwepay = new WechatAppPayOrder();
		    	retMap = appwepay.doThirdPayDisposeOrder(order, null, imei, ip);
		    	orderInfoDAO.update(order);
		    	break;
		 }
		 
		 return retMap;
	}

	
	private void recordOrder(OrderInfo order,UserInfo user) throws BaseException{
		 if(userThirdPayDAO.getUserThirdPayByOrderSn(order.getOrdersn()) == null && !"3".equals(order.getPaytype())){
			 UserThirdPay thirdPay = new UserThirdPay();
			 thirdPay.setOrdersn(order.getOrdersn());
			 thirdPay.setPayid(UUIDGenerator.getUUID());
			 thirdPay.setPaymoney(order.getSellprice());
			 thirdPay.setPlatform(order.getPaytype());
			 thirdPay.setState("1");
			 thirdPay.setUserid(user.getUserId());
			 thirdPay.setPaytype(order.getOrdertype());
			 userThirdPayDAO.insert(thirdPay);
		 }
	}
	
	private void payFromUserAccount(OrderInfo order,UserInfo user,String payPass) throws BaseException{
		
		UserAccountPayOrder parOrder = new UserAccountPayOrder();
    	parOrder.doUserAccountCheck(order, user, payPass);
    	parOrder.doUserAccountPayDisposeOrder(order);
    	
    	order.setPaytime(new Date());
		order.setOrderstate(Constants.ORDER_YES_PAY);
		orderInfoDAO.update(order);
		
		//如果是一份购订单
		if("1".equals(order.getIsOnecent()) && "0".equals(user.getIsOnecent())){
			user.setIsOnecent("1");
			userInfoDAO.updateUser(user);
		}else if("1".equals(user.getIsfreshman())){
			user.setIsfreshman("0");
			userInfoDAO.updateUser(user);
		}
		//修改代金券状态
		if("1".equals(order.getCouponflag())){
			couponUseInfoDAO.updateFinished(user.getUserId(), order.getOrdersn());
		}
		
		Orderprocessinfo orderprocessinfo = new Orderprocessinfo();
	    orderprocessinfo.setOrdersn(order.getOrdersn());
	    orderprocessinfo.setProcessdesc("订单支付成功");
	    orderprocessinfo.setProcessid(IdGenerator.instance().generate(""));
	    orderprocessinfo.setProcesstime(DateUtil.toStr(new Date(), "yyyy-MM-dd HH:mm:ss"));
	    orderprocessinfoDAO.insert(orderprocessinfo);
	}

	@Override
	public SwitchPayTypeResponseBean doSwitchPayType(
			SwitchPayTypeRequestBean requestBean) throws BaseException {
		SwitchPayTypeResponseBean responseBean = new SwitchPayTypeResponseBean();
		
		OrderInfo order = orderInfoDAO.getByOrderSn(requestBean.getOrdersn());
		 //给订单所有金额字段 先都默认为0 共8个
	    order.setCouponamt(new BigDecimal("0"));
	    order.setDeductamt(new BigDecimal("0"));
	    order.setPrimeprice(new BigDecimal("0"));
	    order.setFirstorderamt(new BigDecimal("0"));
	    order.setServicefee(new BigDecimal("0"));
	    order.setDeliveryfee(new BigDecimal("0"));
	    order.setSellprice(new BigDecimal("0"));
	    order.setSuccessprice(new BigDecimal("0"));
	    order.setPaytype(requestBean.getPaytype());
		   
		List<OrderDetail> orderDetailList = 
				orderDetailDAO.getDetailListByOrderSn(requestBean.getOrdersn());
		
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
		
		BigDecimal actuPrice = new BigDecimal("0");//实际销售总金额
		boolean deductFlag = false;
		BigDecimal detailPrice = new BigDecimal("0");
		BigDecimal primeprice = new BigDecimal("0");//实际商品总金额
		
		for(OrderDetail od:orderDetailList){
			if(!deductFlag && "1".equals(od.getProd().getDeductFlag())){
				deductFlag = true;
			}
			if(!"3".equals(requestBean.getPaytype())){
				ProdInfo prodInfo = od.getProd();
				if(od.getSellprice().doubleValue()>0){
					int buyQuantity = od.getQuantity();
					
					if("1".equals(prodInfo.getIsactivity())){
				    	 //如果是活动商品
					    ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
					    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
					    actuPrice = actuPrice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
					    detailPrice = activeInfo.getActivePrice();
				    }else if("1".equals(prodInfo.getIsfreshman())){
				    	actuPrice = actuPrice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
				    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
				    	detailPrice = prodInfo.getFreshmanprice();
				    }else{
				    	actuPrice = actuPrice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
				    	primeprice = primeprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
				    	detailPrice = prodInfo.getSellPrice();
				    	od.setSellprice(detailPrice);
	                    orderDetailDAO.doSwitchOrderDetailPrice(od);
				    }
				}
				
			}else{
				ProdInfo prodInfo = od.getProd();
				if(od.getSellprice().doubleValue()>0){
					int buyQuantity = od.getQuantity();
					
					if("1".equals(prodInfo.getIsactivity())){
				    	 //如果是活动商品
					    ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());
					    actuPrice = actuPrice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
					    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(buyQuantity)));
					    detailPrice = activeInfo.getActivePrice();
				    }else if("1".equals(prodInfo.getIsfreshman())){
				    	actuPrice = actuPrice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
				    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(buyQuantity)));
				    	detailPrice = prodInfo.getFreshmanprice();
				    }else{
				    	actuPrice = actuPrice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
				    	primeprice = primeprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(buyQuantity)));
				    	detailPrice = prodInfo.getSellPrice();
				    	od.setSellprice(detailPrice);
	                    orderDetailDAO.doSwitchOrderDetailPrice(od);
				    }
				}
				
			}
			
		}
		
		order.setPrimeprice(primeprice);//设置实际商品总金额
		
		//查询用户所有未使用代金券列表
		List<CouponUseInfo> couponUseList = couponUseInfoDAO.getUsefulCouponList(order.getUserInfo().getUserId());
		//查询用户目前可使用的代金券列表
		List<VoucherBean> ticketList = settleCartInfoService.queryIsUseCoupon(actuPrice,couponUseList);
		
		VoucherBean voucherBean = null;
		if(ticketList!=null && ticketList.size()>0)
		{
			voucherBean = ticketList.get(0);
		}
		
		 
		    * 1 先判断是否有满赠商品活动
			* 2 再判断是否有满减商品活动
			* 3 最后再判断是否有代金券使用
			* 以上三种活动互斥
			
		
		//将编辑标识置为true
		boolean editDetailFlag = true;
		settleCartInfoService.caclDiscountAmount(order, actuPrice, 
				   orderDetailList, deductFlag,voucherBean,editDetailFlag);
		 
		//总优惠金额 = 满减+代金券金额(二者互斥,一方有值,另一方为零)
		BigDecimal discountAmt = order.getDeductamt().add(order.getCouponamt());
		
		//配送费使用总优惠后的金额计算
		order.setDeliveryfee("1".equals(order.getPayflag())?
				settleCartInfoService.calcDeliverFee(actuPrice.subtract(discountAmt),order.getBusinessUser()):new BigDecimal("0"));
		
		order.setSuccessprice(actuPrice
				.add(order.getDeliveryfee())
                .add(order.getServicefee())
                .subtract(order.getDeductamt())
                .subtract(order.getFirstorderamt())
                .subtract(order.getCouponamt()));
		
	    order.setSellprice(actuPrice
				.add(order.getDeliveryfee())
                .add(order.getServicefee())
                .subtract(order.getDeductamt())
                .subtract(order.getFirstorderamt())
                .subtract(order.getCouponamt()));
		
		
	     orderInfoDAO.doSwitchOrderAmt(order);
	   
		
        // 如果有满赠或满减活动，就不显示代金券
		if("1".equals(order.getCouponflag())
				&& order.getCouponamt().compareTo(new BigDecimal("0")) > 0){
			
			if(!ObjectTools.isNullByObject(voucherBean)){
				responseBean.setTicketdetail(voucherBean);
				responseBean.setUseticketcount(String.valueOf(ticketList.size()));
				responseBean.setTicketList(ticketList);
			}else{
				responseBean.setUseticketcount("0");
			}
			
		}
		
		responseBean.setDiscountamt(discountAmt.toString());//总优惠
		responseBean.setDeliverfee(order.getDeliveryfee().toString());
		
		//构建返回详细信息
	    retDetailBeanList=bulidRetDetailBean(orderDetailList);
		
		responseBean.setDetaillist(retDetailBeanList);
		responseBean.setSellprice(order.getSellprice().toString());
		
		
		
		return responseBean;
	}
	
	public List<OrderDetailBean> bulidRetDetailBean(List<OrderDetail>  orderDetailList){
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
	
		for(OrderDetail orderDetail:orderDetailList){
		    OrderDetailBean retDetailBean = new OrderDetailBean();
		    ProdInfo prodInfo = prodInfoDAO.getById(orderDetail.getProd().getProdid());
			retDetailBean.setImageurl(prodInfo.getImageUrl());
			retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
			retDetailBean.setProdname(orderDetail.getProdname());
			retDetailBean.setProdnum(orderDetail.getProd().getProdid());
			retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
			retDetailBean.setSellflag(prodInfo.getPreSellTag());
			int buyQuantity = orderDetail.getQuantity();
			retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(buyQuantity)).toString());
			retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
			
			retDetailBeanList.add(retDetailBean);
		}
         
		return retDetailBeanList;
	}
	
	
	public OrderDetailBean bulidRetDetailBean(OrderDetail orderDetail,ProdInfo prodInfo){
	    OrderDetailBean retDetailBean = new OrderDetailBean();
		retDetailBean.setImageurl(prodInfo.getImageUrl());
		retDetailBean.setPreselltime(DateUtil.toStr(prodInfo.getPreSellTime(), "yyyy-MM-dd"));
		retDetailBean.setProdname(prodInfo.getProdname());
		retDetailBean.setProdnum(prodInfo.getProdid());
		retDetailBean.setQuantity(orderDetail.getQuantity().toString());	
		retDetailBean.setSellflag(prodInfo.getPreSellTag());
		retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(orderDetail.getQuantity())).toString());
		retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
		
		return retDetailBean;
	}
	
	

	
}*/
