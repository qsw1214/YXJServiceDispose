package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ICouponUseInfoDAO;
import com.youxianji.dao.IYxjUserCouponShareDAO;
import com.youxianji.dao.IYxjUserCouponShareDetailDAO;
import com.youxianji.facade.coupon.bean.ReceiveShareCouponResponseBean;
import com.youxianji.facade.trade.bean.json.VoucherBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjUserCouponShareDetail;
import com.youxianji.service.IYxjUserShareCouponDetailService;
import com.youxianji.util.DateUtil;

import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

@Service("yxjUserShareCouponDetailService")
public class YxjUserShareCouponDetailServiceImpl implements IYxjUserShareCouponDetailService{
	
	@Resource
	private IYxjUserCouponShareDetailDAO yxjUserCouponShareDetailDAO;
	@Resource
	private ICouponUseInfoDAO couponUseInfoDAO;

	@Override
	public List<YxjUserCouponShareDetail> findCouponShareDetailByShareId(String shareId) {
		
		return yxjUserCouponShareDetailDAO.findCouponShareDetailByShareId(shareId);
	}

	@Override
	public YxjUserCouponShareDetail getOneShareDetailByShareId(String shareId) {
		
		return yxjUserCouponShareDetailDAO.getOneShareDetailByShareId(shareId);
	}

	@Override
	public YxjUserCouponShareDetail validateIsReceivedByShareId(String shareId, String userId) {
		
		return yxjUserCouponShareDetailDAO.validateIsReceivedByShareId(shareId, userId);
	}

	@Override
	public ReceiveShareCouponResponseBean doGetOneCoupon(String userId, String shareId,
			ReceiveShareCouponResponseBean responseBean) {
		//随机领取一张红包
		YxjUserCouponShareDetail shareDetail = this.getOneShareDetailByShareId(shareId);
		
		if(ObjectTools.isNullByObject(shareDetail)){
			//返回为空，则返回该红包已经领完
			responseBean.setResultflag("2");
			
			return responseBean;
		}else{
			//插入该用户的红包使用表一条记录，红包标题设置为“通用红包”
			this.giveCouponTicket(userId, shareDetail, responseBean);
			
			//返回该红包领取成功
			responseBean.setResultflag("0");
			
			return responseBean;
		}
	}
	
	
	//发放红包操作
	private void giveCouponTicket(String userId,YxjUserCouponShareDetail shareDetail,ReceiveShareCouponResponseBean responseBean){
		HashMap<String, Object> map=new HashMap<>();
		map.put("state", "1");
		map.put("cuid", UUIDGenerator.getUUID());
		map.put("userId", userId);
		
        map.put("couponid", shareDetail.getCouponId());
        map.put("couponAmount", shareDetail.getCouponAmount());
        map.put("useBeginTime", shareDetail.getBeginTime());
        map.put("useEndTime", shareDetail.getEndTime());
        map.put("sinceMoney", shareDetail.getSinceMoney());
        map.put("couponName", shareDetail.getCouponName());
        map.put("couponDesc", shareDetail.getCouponDesc());
        map.put("couponTitle", shareDetail.getCouponTitle());
        couponUseInfoDAO.insert(map);
       
    	VoucherBean ticketdetail = new VoucherBean();
    	ticketdetail.setDatebegin(DateUtil.toStr((Date)map.get("useBeginTime"), "yyyy-MM-dd"));
    	ticketdetail.setDateend(DateUtil.toStr((Date)map.get("useEndTime"), "yyyy-MM-dd"));
    	ticketdetail.setDescrib((String) map.get("couponDesc"));
    	ticketdetail.setTicketid((String)map.get("cuid").toString());
    	ticketdetail.setTickettitle((String)map.get("couponTitle"));
    	ticketdetail.setValue(String.valueOf((BigDecimal)map.get("couponAmount")));
        
        UserInfo receiveUser = new UserInfo();
        receiveUser.setUserId(userId);
        shareDetail.setReceiveUser(receiveUser);
        shareDetail.setReceiveTime(new Date());
        this.updateShareDetail(shareDetail);	
        
        responseBean.setTicketdetail(ticketdetail);
	}

	@Override
	public void updateShareDetail(YxjUserCouponShareDetail shareDetail) {
		
		yxjUserCouponShareDetailDAO.update(shareDetail);
	}
}	

