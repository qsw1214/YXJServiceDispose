package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IActivityProdInfoDAO;
import com.youxianji.dao.IAfternoonteaDAO;
import com.youxianji.dao.ICartInfoDAO;
import com.youxianji.dao.IDeductAmtRulesDAO;
import com.youxianji.dao.IDeductProdDAO;
import com.youxianji.dao.IDeliveryAddressDAO;
import com.youxianji.dao.IDeliveryFeeRulesDAO;
import com.youxianji.dao.IOrderDetailDAO;
import com.youxianji.dao.IOrderInfoDAO;
import com.youxianji.dao.IOrderTeaBindingDAO;
import com.youxianji.dao.IProdInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.facade.trade.bean.SettleCartInfoResponseBean;
import com.youxianji.facade.trade.bean.json.CartDetailBean;
import com.youxianji.facade.trade.bean.json.OrderDetailBean;
import com.youxianji.facade.trade.bean.json.ReceiveAddrBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.ActivityProdInfo;
import com.youxianji.pojo.BusinessUserInfo;
import com.youxianji.pojo.CartInfo;
import com.youxianji.pojo.CouponRuleInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.DeductAmtRules;
import com.youxianji.pojo.DeductProd;
import com.youxianji.pojo.DeliveryAddress;
import com.youxianji.pojo.DeliveryFeeRules;
import com.youxianji.pojo.OrderDetail;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.service.ISettleCartInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Service("settleCartInfoService")
public class SettleCartInfoServiceImpl implements ISettleCartInfoService {
	Logger logger = Logger.getLogger(SettleCartInfoServiceImpl.class);
    @Resource
    private IUserInfoDAO userInfoDAO;
    @Resource
    private ICartInfoDAO cartInfoDAO;
    @Resource
    private IOrderInfoDAO orderInfoDAO;
    @Resource 
    private IOrderDetailDAO orderDetailDAO;
    @Resource
    private ICouponUseInfoService couponUseInfoService;
    @Resource
    private IDeliveryAddressDAO deliveryAddressDAO;
    @Resource
    private IActivityProdInfoDAO activityProdInfoDAO;
    @Resource
    private IDeliveryFeeRulesDAO deliveryFeeRulesDAO;
    @Resource
    private IAfternoonteaDAO afternoonteaDAO;
    @Resource
    private IOrderTeaBindingDAO orderTeaBindingDAO;
    @Resource
    private IDeductAmtRulesDAO deductAmtRulesDAO;
    @Resource
    private IDeductProdDAO deductProdDAO;
    @Resource
    private IProdInfoDAO prodInfoDAO;
	
	@Override
	public SettleCartInfoResponseBean doSettleCartInfo(String userId,String os,String deviceNo,List<CartDetailBean> detailList,String invitecode)
			throws BaseException {
        //这里需要加一些校验
		UserInfo userInfo = userInfoDAO.getById(userId);
		String orderSn = IdGenerator.instance().generate(os);
		//查询是否存在该订单号
		if(orderInfoDAO.getByOrderSn(orderSn) != null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("订单号重复，请重新结算");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		BusinessUserInfo businessUser = null; 
		List<OrderDetailBean> retDetailBeanList = new ArrayList<OrderDetailBean>();
		List<OrderDetail>  orderDetailList = new ArrayList<OrderDetail>();
		String payflag = null;
		boolean deductFlag = false;
		BigDecimal primeprice = new BigDecimal("0");
		BigDecimal sellprice = new BigDecimal("0");
		BigDecimal detailPrice = new BigDecimal("0");
		Set<String> acProdSet = new HashSet<>();
		for(CartDetailBean cartBean:detailList){
			CartInfo cartInfo = cartInfoDAO.getById(cartBean.getCartid());
			if(cartInfo == null){
            	ErrorEnum.valueOf("FAIL_5555").changetMessage("购物车不能为空");
        		throw new BaseException(ErrorEnum.FAIL_5555);
            }
		    ProdInfo prodInfo = cartInfo.getProdInfo();
		    if(businessUser == null){
		    	businessUser = prodInfo.getBusinessUser();
		    }
            if(payflag != null && !payflag.equals(prodInfo.getPayflag())){
            	ErrorEnum.valueOf("FAIL_5555").changetMessage("配送和自提商品不能混合购买");
        		throw new BaseException(ErrorEnum.FAIL_5555);
            }
            payflag = prodInfo.getPayflag();
            
		    if("1".equals(prodInfo.getDeductFlag())){
		    	deductFlag = true;
		    }
		    
		    ActivityProdInfo activeInfo =  activityProdInfoDAO.getActiveByProdId(prodInfo.getProdid());

		    if(activeInfo != null && "1".equals(prodInfo.getIsactivity())){
		    	 //如果是活动商品
			   			    
			   checkActivityProd(activeInfo, prodInfo
			    		 , cartInfo.getProdQuantity(), acProdSet);
			    
			    primeprice = primeprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
		    	sellprice = sellprice.add(activeInfo.getActivePrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
		    	detailPrice = activeInfo.getActivePrice();
		    }else if("1".equals(prodInfo.getIsfreshman())){
		    	//如果是新人专享商品
		    	checkFreshman(userInfo,cartInfo.getProdQuantity());
		    	primeprice = primeprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
		    	sellprice = sellprice.add(prodInfo.getFreshmanprice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
		    	detailPrice = prodInfo.getFreshmanprice();
		    }else{
		    	 //改为都按原价计算
		    	 primeprice = primeprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			     sellprice = sellprice.add(prodInfo.getSellPrice().multiply(new BigDecimal(cartInfo.getProdQuantity())));
			     detailPrice = prodInfo.getSellPrice();
		    }

		    //创建订单详细
		    orderDetailList.add(bulidOrderDetail(cartInfo,orderSn,detailPrice));
		    
		}
		
		//查询用户所有未使用代金券列表
		List<CouponUseInfo> couponUseList = couponUseInfoService.getUsefulCouponList(userInfo.getUserId());
		//查询用户目前可使用的代金券列表
		List<VoucherBean> ticketList  = this.queryIsUseCoupon(sellprice,couponUseList);
		//用户默认使用的代金券信息 BEAN
		VoucherBean voucherBean = null;
		if(ticketList!=null && ticketList.size()>0)
		{
			voucherBean = ticketList.get(0);
		}
		// 创建订单
		OrderInfo order = bulidOrderInfo(orderSn,primeprice,sellprice
				,deviceNo,userInfo,businessUser,deductFlag,payflag,orderDetailList,voucherBean);
		//构建返回详细信息
	    retDetailBeanList=bulidRetDetailBean(orderDetailList);
	    
		return bulidResponseBean(order,retDetailBeanList,ticketList);
	}
	
	
	
	public BigDecimal calcDeliverFee(BigDecimal sellprice,BusinessUserInfo businessUser){
		List<DeliveryFeeRules> list =deliveryFeeRulesDAO.getListByBuserId(businessUser.getBuserId());
	    BigDecimal deductAmt = new BigDecimal("0");
		for(int i=0;i<list.size();i++){
			DeliveryFeeRules dr = list.get(i);
			if(sellprice.compareTo(dr.getSinceprice()) >= 0){
				deductAmt = dr.getDeliverfee();
				break;
			}
		}
		return deductAmt;
	}
	
	public BigDecimal calcDeductAmt(BigDecimal sellprice){
		List<DeductAmtRules> list =deductAmtRulesDAO.getList();
	    BigDecimal deductAmt = new BigDecimal("0");
	    Date today = new Date();
		for(int i=0;i<list.size();i++){
			DeductAmtRules dr = list.get(i);
			if(today.compareTo(dr.getBegintime()) >= 0 && today.compareTo(dr.getEndtime()) <= 0){
				if(sellprice.compareTo(dr.getSinceprice()) >= 0){
					deductAmt = dr.getDeductamt();
					break;
				}
			}
		
		}
		return deductAmt;
	}
	
	
	public String queryDeductProd(BigDecimal sellprice){
		List<DeductProd> list = deductProdDAO.getList();
	    String deductProdId = null;
	    Date today = new Date();
		for(int i=0;i<list.size();i++){
			DeductProd dr = list.get(i);
			if(today.compareTo(dr.getBegintime()) >= 0 && today.compareTo(dr.getEndtime()) <= 0){
				if(sellprice.compareTo(dr.getSinceamount()) >= 0){
					deductProdId = dr.getProdid();
					break;
				}
			}
		
		}
		return deductProdId;
	}
	
    private void checkActivityProd(ActivityProdInfo activeInfo
    		,ProdInfo prodInfo,int prodQuantity,Set<String> acProdSet) throws BaseException{
    	if(activeInfo == null){
 	    	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"活动产品状态不正确");
     		throw new BaseException(ErrorEnum.FAIL_5555);
 	    }
    	 
		Date currentTime = new Date();
    	if(currentTime.compareTo(activeInfo.getActivitybegin()) < 0
	    		&& currentTime.compareTo(activeInfo.getActivityend()) > 0){
	    	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"不在活动时间，请核查");
    		throw new BaseException(ErrorEnum.FAIL_5555);
	    }
	    if(prodQuantity > 1
	    		|| acProdSet.size() >1){
	    	ErrorEnum.valueOf("FAIL_5555").changetMessage(prodInfo.getProdname()+"不能购买多个活动商品");
    		throw new BaseException(ErrorEnum.FAIL_5555);
	    }
	    acProdSet.add(activeInfo.getActivityid());
    	
    }
    
   private void checkFreshman(UserInfo userInfo,int quantity) throws BaseException{
	    if(!"1".equals(userInfo.getIsfreshman())){
	   		ErrorEnum.valueOf("FAIL_5555").changetMessage("您不是新人,不能购买该新人专享类商品");
	   		throw new BaseException(ErrorEnum.FAIL_5555);
	   	}
	    
    	if(quantity >1){
    		ErrorEnum.valueOf("FAIL_5555").changetMessage("新人专享商品不能大于1");
    		throw new BaseException(ErrorEnum.FAIL_5555);
    	}
    	
    	
    	
    	
    }
    
	public SettleCartInfoResponseBean bulidResponseBean(OrderInfo order,List<OrderDetailBean> retDetailBeanList,List<VoucherBean> ticketList){
		SettleCartInfoResponseBean responseBean = new SettleCartInfoResponseBean();
		
		responseBean.setDeliverfee(order.getDeliveryfee().toString());
		BigDecimal discountAmt = order.getDeductamt().add(order.getCouponamt());
		responseBean.setDiscountamt(discountAmt.toString());//总优惠
		responseBean.setOrdersn(order.getOrdersn());
		ReceiveAddrBean receiveaddress = new ReceiveAddrBean();
		List<DeliveryAddress> delAddressList =  deliveryAddressDAO.getAddressListByUserId(order.getUserInfo().getUserId());
		if(delAddressList != null && "1".equals(order.getPayflag())){
			for(DeliveryAddress addr:delAddressList){
				if("1".equals(addr.getIsdefault())){
					receiveaddress.setReceiveaddress(addr.getReceiveaddress());
					receiveaddress.setReceiveid(addr.getDaid());
					receiveaddress.setReceivename(addr.getReceivename());
					receiveaddress.setReceivephone(addr.getTelephone());
					receiveaddress.setReceiveprovince(addr.getProvince());
					receiveaddress.setReceivecity(addr.getCity());
					receiveaddress.setReceivedistrict(addr.getDistrict());
					responseBean.setReceiveaddress(receiveaddress);
					break;
				}
			}
		}
		
		responseBean.setSellprice(order.getSellprice().toString());
		responseBean.setServicefee(order.getServicefee().toString());
		// 查询该用户的代金券列表
		// 如果有满赠或满减活动，就不显示代金券
		if("1".equals(order.getCouponflag())
				&& order.getCouponamt().compareTo(new BigDecimal("0")) > 0){
			
			if(ticketList!=null && ticketList.size()>0)
			{
				VoucherBean voucherBean = ticketList.get(0);
				responseBean.setTicketdetail(voucherBean);
				responseBean.setUseticketcount(String.valueOf(ticketList.size()));
				responseBean.setTicketList(ticketList);
			}else{
				responseBean.setUseticketcount("0");
			}
		}
		
		
		
		responseBean.setPayflag(order.getPayflag());
		//22:00前下单，则返回1 明天 22:00之后下单，则返回2 后天
		Date today = new Date();
		if(today.compareTo(DateUtil.toDate(DateUtil.toStr(today, "yyyy-MM-dd")+" 22:00:00","yyyy-MM-dd HH:mm:ss")) > 0 ){
			responseBean.setSendday("2");
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextSomeDay(today, 2), "MM-dd"));
		}else{
			responseBean.setSendday("1");
			responseBean.setSpecificday(DateUtil.toStr(DateUtil.nextDay(today), "MM-dd"));
		}
		
		responseBean.setDetaillist(retDetailBeanList);
		  
		return responseBean;
	}
	
	
	public List<VoucherBean> queryIsUseCoupon(BigDecimal sellprice
			,List<CouponUseInfo> couponUseList){
		List<VoucherBean> resultList = new ArrayList<VoucherBean>();
		if(couponUseList != null && couponUseList.size() > 0){
			for(CouponUseInfo couponUse:couponUseList){
				if(sellprice.compareTo(new BigDecimal(couponUse.getCouponRuleInfo().getSinceMoney())) > 0){

					VoucherBean ticketdetail = new VoucherBean();
					CouponRuleInfo ruleInfo = couponUse.getCouponRuleInfo();
					ticketdetail.setDatebegin(DateUtil.toStr(ruleInfo.getBeginTime(), "yyyy-MM-dd HH:mm:ss"));
					ticketdetail.setDateend(DateUtil.toStr(ruleInfo.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
					ticketdetail.setDescrib(ruleInfo.getCouponDesc());
					ticketdetail.setTicketid(couponUse.getCuid());
					ticketdetail.setTickettitle(ruleInfo.getCouponName());
					ticketdetail.setValue(couponUse.getCouponAmount().toString());
					
					resultList.add(ticketdetail);
				}
			}
			
		}
		
		return resultList;
		
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
			retDetailBean.setSellprice(orderDetail.getSellprice().multiply(new BigDecimal(orderDetail.getQuantity())).toString());
			retDetailBean.setUnitprice(orderDetail.getSellprice().toString());
			
			retDetailBeanList.add(retDetailBean);
		}
         
		return retDetailBeanList;
	}
	
	

	
	public OrderDetail bulidOrderDetail(CartInfo cartInfo,String orderSn,BigDecimal detailPrice){
		 OrderDetail orderDetail = new OrderDetail();
		 orderDetail.setDetailid(UUIDGenerator.getUUID());
	     orderDetail.setIsreview("0");
	     orderDetail.setOrdersn(orderSn);
	     orderDetail.setProd(cartInfo.getProdInfo());
	     orderDetail.setProdname(cartInfo.getProdInfo().getProdname());
	     orderDetail.setQuantity(cartInfo.getProdQuantity());
	     orderDetail.setSellprice(detailPrice);
	     orderDetail.setRedundancefir("N");
	     
	     return orderDetail;
	}
	
	/**
	 * 
	 * @param orderSn
	 * @param primeprice
	 * @param sellprice
	 * @param deviceNo
	 * @param userInfo
	 * @param businessUser
	 * @param deductFlag
	 * @param payflag
	 * @param orderDetailList
	 * @return
	 */
	public OrderInfo bulidOrderInfo(String orderSn,BigDecimal primeprice,
			BigDecimal sellprice,String deviceNo,
			UserInfo userInfo,BusinessUserInfo businessUser
			,boolean deductFlag,String payflag
			,List<OrderDetail>  orderDetailList,VoucherBean voucherBean){
		   OrderInfo order = new OrderInfo();
		   order.setCouponflag("0");
		   //给订单所有金额字段 先都默认为0 共8个
		   order.setCouponamt(new BigDecimal("0"));
		   order.setDeductamt(new BigDecimal("0"));
		   order.setFirstorderamt(new BigDecimal("0"));
		   order.setServicefee(new BigDecimal("0"));
		   order.setDeliveryfee(new BigDecimal("0"));
		   order.setSellprice(new BigDecimal("0"));
		   order.setPrimeprice(new BigDecimal("0"));
		   order.setSuccessprice(new BigDecimal("0"));
		   
		   order.setReceiveaddress(null);
		   order.setReceivename(null);
		   order.setReceivetelephone(null);
		   order.setReceivetime(null);
		   order.setRemark(null);
		   order.setPaytime(null);
		   order.setPaytype("3");//默认余额支付
		   order.setPayflag(payflag);
		   order.setOrdertype("C");
		   
		   //计算配送 费
		   order.setDeviceno(deviceNo);
		   order.setOrdersn(orderSn);
		   order.setOrderstate(Constants.ORDER_INIT);
		   order.setOrdertime(new Date());
		   order.setUserInfo(userInfo);
		   order.setBusinessUser(businessUser);
			
		   order.setPrimeprice(primeprice);
		   order.setSellprice(sellprice);
		   /*
		    * 1 先判断是否有满赠商品活动
			* 2 再判断是否有满减商品活动
			* 3 最后再判断是否有代金券使用
			* 以上三种活动互斥
			*/
		   caclDiscountAmount(order, sellprice, 
				   orderDetailList, deductFlag,voucherBean,false);
		   
		 
		    //总优惠金额 = 满减+代金券金额(二者互斥,一方有值,另一方为零)
			BigDecimal discountAmt = order.getDeductamt().add(order.getCouponamt());
		   /*
		    * 优惠后计算配送费
		    */
		   order.setDeliveryfee("1".equals(order.getPayflag())?
					calcDeliverFee(sellprice.subtract(discountAmt)
							,order.getBusinessUser()):new BigDecimal("0"));
			 
		   order.setSuccessprice(sellprice
					.add(order.getDeliveryfee())
	                .add(order.getServicefee())
	                .subtract(order.getDeductamt())
	                .subtract(order.getFirstorderamt())
	                .subtract(order.getCouponamt()));
			
		   order.setSellprice(sellprice
					.add(order.getDeliveryfee())
	                .add(order.getServicefee())
	                .subtract(order.getDeductamt())
	                .subtract(order.getFirstorderamt())
	                .subtract(order.getCouponamt()));
		   
			
		   order.setIsOnecent("0");
		   order.setInvitecode(null);
		   
			
		   this.saveALLOrder(order, orderDetailList);
			
			
		   return order;
	}
	
	public void caclDiscountAmount(OrderInfo order,BigDecimal sellprice
			,List<OrderDetail> orderDetailList,boolean deductFlag,VoucherBean voucherBean,boolean editDetailFlag){
		
		String deductProdId = this.queryDeductProd(sellprice);
		BigDecimal deductAmt = this.calcDeductAmt(sellprice);
		
		
		for(int i=0;i<orderDetailList.size();i++){
			   OrderDetail od = orderDetailList.get(i);
			
			   if("Y".equals(od.getRedundancefir())){
				   
				  //如果该商品数量只有一个，则删除该商品
				  orderDetailList.remove(od);
				  
				//如果是切换支付方式下的存在满赠商品，则订单明细做数据库删除操作
				  if(editDetailFlag){
					  //删除订单明细
					  orderDetailDAO.delete(od.getDetailid());
				  }
				   
			   }
		   }
		  //满减金额置0,重新计算
		   order.setDeductamt(new BigDecimal(0));
		   //代金券金额置0，使用代金券标识置0，重新计算
		   order.setCouponamt(new BigDecimal(0));
		   order.setCouponflag("0");
		
		 if(!ObjectTools.isNullByString(deductProdId)){
			  //首先判断满赠条件
			 
				     ProdInfo deductProd = prodInfoDAO.getById(deductProdId);
					 OrderDetail orderDetail = new OrderDetail();
					 orderDetail.setDetailid(UUIDGenerator.getUUID());
				     orderDetail.setIsreview("0");
				     orderDetail.setOrdersn(order.getOrdersn());
				     orderDetail.setProd(deductProd);
				     orderDetail.setProdname(deductProd.getProdname());
				     orderDetail.setQuantity(1);
				     orderDetail.setSellprice(new BigDecimal(0));
				     orderDetail.setRedundancefir("Y");//设置为赠品标记
				     orderDetailList.add(orderDetail);
				     
				     if(editDetailFlag){
				    	 //如果是切换支付方式下的增加满赠商品，则订单明细做数据库插入操作
				    	 orderDetailDAO.save(orderDetail);
				     }
			   
		  }else if(deductFlag && deductAmt.compareTo(new BigDecimal("0")) > 0){
			   //不符合满赠条件，就判断满减条件
			   order.setDeductamt(deductAmt);
		  }else{
			   //不符合满减条件，最后再判断是否可以使用代金券
			  if(voucherBean != null){
				  order.setCouponamt(new BigDecimal(voucherBean.getValue()));
				  order.setCouponflag("1");  
			  }
			  
		  }
	}
	
	
	private void saveALLOrder(OrderInfo order,List<OrderDetail>  orderDetailList){
		//保存订单
		orderInfoDAO.insert(order);
		//保存订单详细
		for(OrderDetail od:orderDetailList){
			orderDetailDAO.save(od);
		}
	}


}
