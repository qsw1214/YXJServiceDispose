package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class OrderInfo {

	 private String ordersn;//'订单号',
	 private UserInfo userInfo;//'购买用户ID',
	 private BusinessUserInfo businessUser;//'销售商户ID',
	 private BigDecimal primeprice;//'订单未优惠的商品总价',
	 private BigDecimal sellprice;//实际支付价格(包括商品总价-优惠总价+配送费+服务费等)
	 private BigDecimal successprice;//转账金额
	 private BigDecimal deductamt;//'满减金额',
	 private BigDecimal firstorderamt;//'首单立减金额',
	 private BigDecimal couponamt;//'代金券金额',
	 private BigDecimal deliveryfee;//'配送费',
	 private BigDecimal servicefee;//'服务费',
	 private Date ordertime;//'下单时间',
	 private Date paytime;//'支付时间',
	 private String payflag;//'配送标记 1.配送 2.自取',
	 private String paytype;//'支付方式 1. 微信公众号支付，2. 支付宝支付，3.余额支付,4 微信APP支付
	 private String couponflag;//'使用优惠券标记 0.未使用 1.已使用',
	 private String receivetelephone;//'收货人手机号',
	 private String receivename;//'收货人姓名',
	 private String receiveaddress;//'收货人地址',
	 private String receivebuilding;
	 private String receivelongitude;
	 private String receivelatitude;
	 private Date receivetime;//'到货时间',
	 private String deviceno;//设备编号',
	 private String remark;//'订单备注',
	 private String orderstate;//'订单状态 0.初始 1.待付款 2.已支付 3.已到货 4.已到货 5.订单取消 6.退货中 7.已退货
	 private String cargocode;// 收货确认码
	 private String distributionInterval;//配送时间段
	 private SubStoreInfo store;
	 /**是否是一份购订单**/
	 private String isOnecent;//是否为新人 0.不是 1.是
	 private String invitecode;
	 private String ordertype;//'订单分类  B.商家端订单 C.用户端订单',
	 
	 private BigDecimal bargainamt;
	 private String invoice_flag;
	 private String receiveprovince;//	String 收货省份
	 private String receivecity;//	String 收货城市
	 private String receivedistrict;//	String 收货区县
	 private String sourcetype;//订单货源 类型 1库房直发单 2 产地直发单
	 private BaseOrderInfo baseordersn;//所属总订单号
	 private String waybillno;//运单号
	 
	 private String suppilerid;
	 
 
	 
	 
	 

	public String getSuppilerid() {
		return suppilerid;
	}
	public void setSuppilerid(String suppilerid) {
		this.suppilerid = suppilerid;
	}
	public BaseOrderInfo getBaseordersn() {
		return baseordersn;
	}
	public void setBaseordersn(BaseOrderInfo baseordersn) {
		this.baseordersn = baseordersn;
	}
	public String getWaybillno() {
		return waybillno;
	}
	public void setWaybillno(String waybillno) {
		this.waybillno = waybillno;
	}
	public String getSourcetype() {
		return sourcetype;
	}
	public void setSourcetype(String sourcetype) {
		this.sourcetype = sourcetype;
	}
	public String getReceiveprovince() {
		return receiveprovince;
	}
	public void setReceiveprovince(String receiveprovince) {
		this.receiveprovince = receiveprovince;
	}
	public String getReceivecity() {
		return receivecity;
	}
	public void setReceivecity(String receivecity) {
		this.receivecity = receivecity;
	}
	public String getReceivedistrict() {
		return receivedistrict;
	}
	public void setReceivedistrict(String receivedistrict) {
		this.receivedistrict = receivedistrict;
	}
	public BigDecimal getBargainamt() {
		return bargainamt;
	}
	public void setBargainamt(BigDecimal bargainamt) {
		this.bargainamt = bargainamt;
	}
	public String getInvoice_flag() {
		return invoice_flag;
	}
	public void setInvoice_flag(String invoice_flag) {
		this.invoice_flag = invoice_flag;
	}
	public String getOrdertype() {
		return ordertype;
	}
	public void setOrdertype(String ordertype) {
		this.ordertype = ordertype;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getIsOnecent() {
		return isOnecent;
	}
	public void setIsOnecent(String isOnecent) {
		this.isOnecent = isOnecent;
	}
	public SubStoreInfo getStore() {
		return store;
	}
	public void setStore(SubStoreInfo store) {
		this.store = store;
	}
	public String getReceivebuilding() {
		return receivebuilding;
	}
	public void setReceivebuilding(String receivebuilding) {
		this.receivebuilding = receivebuilding;
	}
	public String getReceivelongitude() {
		return receivelongitude;
	}
	public void setReceivelongitude(String receivelongitude) {
		this.receivelongitude = receivelongitude;
	}
	public String getReceivelatitude() {
		return receivelatitude;
	}
	public void setReceivelatitude(String receivelatitude) {
		this.receivelatitude = receivelatitude;
	}
	public BigDecimal getSuccessprice() {
		return successprice;
	}
	public void setSuccessprice(BigDecimal successprice) {
		this.successprice = successprice;
	}
	public String getDistributionInterval() {
		return distributionInterval;
	}
	public void setDistributionInterval(String distributionInterval) {
		this.distributionInterval = distributionInterval;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public UserInfo getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	public BusinessUserInfo getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(BusinessUserInfo businessUser) {
		this.businessUser = businessUser;
	}
	public BigDecimal getPrimeprice() {
		return primeprice;
	}
	public void setPrimeprice(BigDecimal primeprice) {
		this.primeprice = primeprice;
	}
	public BigDecimal getSellprice() {
		return sellprice;
	}
	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	public BigDecimal getDeductamt() {
		return deductamt;
	}
	public void setDeductamt(BigDecimal deductamt) {
		this.deductamt = deductamt;
	}
	public BigDecimal getFirstorderamt() {
		return firstorderamt;
	}
	public void setFirstorderamt(BigDecimal firstorderamt) {
		this.firstorderamt = firstorderamt;
	}
	public BigDecimal getCouponamt() {
		return couponamt;
	}
	public void setCouponamt(BigDecimal couponamt) {
		this.couponamt = couponamt;
	}
	public BigDecimal getDeliveryfee() {
		return deliveryfee;
	}
	public void setDeliveryfee(BigDecimal deliveryfee) {
		this.deliveryfee = deliveryfee;
	}
	public BigDecimal getServicefee() {
		return servicefee;
	}
	public void setServicefee(BigDecimal servicefee) {
		this.servicefee = servicefee;
	}
	public Date getOrdertime() {
		return ordertime;
	}
	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}
	public Date getPaytime() {
		return paytime;
	}
	public void setPaytime(Date paytime) {
		this.paytime = paytime;
	}
	public String getPayflag() {
		return payflag;
	}
	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}
	public String getPaytype() {
		return paytype;
	}
	public void setPaytype(String paytype) {
		this.paytype = paytype;
	}
	public String getCouponflag() {
		return couponflag;
	}
	public void setCouponflag(String couponflag) {
		this.couponflag = couponflag;
	}
	public String getReceivetelephone() {
		return receivetelephone;
	}
	public void setReceivetelephone(String receivetelephone) {
		this.receivetelephone = receivetelephone;
	}
	public String getReceivename() {
		return receivename;
	}
	public void setReceivename(String receivename) {
		this.receivename = receivename;
	}
	public String getReceiveaddress() {
		return receiveaddress;
	}
	public void setReceiveaddress(String receiveaddress) {
		this.receiveaddress = receiveaddress;
	}
	public Date getReceivetime() {
		return receivetime;
	}
	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}
	public String getDeviceno() {
		return deviceno;
	}
	public void setDeviceno(String deviceno) {
		this.deviceno = deviceno;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public String getCargocode() {
		return cargocode;
	}
	public void setCargocode(String cargocode) {
		this.cargocode = cargocode;
	}
	 
	 
	

}
