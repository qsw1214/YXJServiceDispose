package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;

import com.youxianji.dao.IMemberCardInfoDAO;
import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IUserRechargeDao;
import com.youxianji.dao.IWeChatUserInfoDAO;
import com.youxianji.dao.IYxjUserEmployeeDAO;
import com.youxianji.pojo.MemberCardInfo;
import com.youxianji.pojo.UserThirdCharge;
import com.youxianji.service.IUserRechargeService;
import com.youxianji.service.orderpay.WechatAppChargeOrder;
import com.youxianji.service.orderpay.WechatPayOrder;
import com.youxianji.service.orderpay.WechatPublicNoChargeOrder;
import com.youxianji.web.util.ErrorEnum;
@Service("userRechargeService")
public class UserRechargeServiceImpl implements IUserRechargeService {

	Logger logger = Logger.getLogger(WechatPayOrder.class);
	
	

	
	@Resource
	private IUserRechargeDao userRechargeDao;
	@Resource
	private IMemberCardInfoDAO memberCardInfoDAO;
	@Resource
	private IWeChatUserInfoDAO weChatUserInfoDAO;
	@Resource
	private IYxjUserEmployeeDAO yxjUserEmployeeDAO; 
	@Resource
	private IUserInfoDAO userInfoDAO;
	
	@Override
	public void userRecharge(String userid, String telephone, String discountid, String normalcharge) {
		
		userRechargeDao.userRecharge(userid, telephone, discountid, normalcharge);

	}

	@Override
	public String queryUserIsmember(String userid, String telephone) {
		
		return userRechargeDao.queryUserIsmember(userid, telephone);
	}

	@Override
	public Map<String, String> doWechatChargeMemberCard(String cardid,String userId,String ip,String payType,String employnum) throws BaseException{
		MemberCardInfo cardInfo = memberCardInfoDAO.findMemberCardInfoById(cardid);
		
		if(!ObjectTools.isNullByString(employnum)){
			if(yxjUserEmployeeDAO.getObject(employnum) == null){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("员工编号在系统中不存在");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			List<UserThirdCharge> userChargeList = userRechargeDao.getListByUserIdAndAmount(userId, cardInfo.getChargeamount().toString());
			if(userChargeList != null && userChargeList.size() > 0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("您已使用过邀请码方式充值，请到商城正常充值");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			
		}
		
		
		
		
		
		String chargeSn = UUIDGenerator.getUUID();
		//保存充值订单
		UserThirdCharge thirdCharge = new UserThirdCharge();
		thirdCharge.setChargemoney(new BigDecimal(cardInfo.getChargeamount()));
		thirdCharge.setChargesn(chargeSn);
		thirdCharge.setPlatform(payType);
		thirdCharge.setState("1");
		thirdCharge.setUserid(userId);
		thirdCharge.setChargetime(new Date());
		thirdCharge.setEmploynum(employnum);
		thirdCharge.setChargetype(userInfoDAO.getById(userId).getUsertype());
		userRechargeDao.insert(thirdCharge);
	    //new BigDecimal(cardInfo.getChargeamount())
		Map<String,String> secSignMap = null;
		if("1".equals(payType)){
			secSignMap = new WechatPublicNoChargeOrder().doWechatPublicNoCharge(chargeSn, new BigDecimal(cardInfo.getChargeamount()), 
					weChatUserInfoDAO.getWeChatUserInfoByUserId(userId).getOpenid(), null, ip);
		}else if("2".equals(payType)){
			secSignMap = new WechatAppChargeOrder().doWechatAppCharge(chargeSn, new BigDecimal(cardInfo.getChargeamount()), null, ip);
		}
		
	     return secSignMap;
	}
	
	
	

}
