package com.youxianji.facade.system;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.LoginRequestBean;
import com.youxianji.facade.system.bean.LoginResponseBean;
import com.youxianji.pojo.CodeForFindPass;
import com.youxianji.pojo.CouponRuleInfo;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserCouponGrantConfig;
import com.youxianji.pojo.YxjUserCouponGrantConfigDetail;
import com.youxianji.service.ICartInfoService;
import com.youxianji.service.ICodeForFindPassService;
import com.youxianji.service.ICouponRuleInfoService;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserCouponGrantConfigService;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="1002",comment="登录接口业务处理")	//自定义标签
@Scope("prototype")	//标签
public class LoginFacade extends AbsFacade{
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ICouponUseInfoService couponUseInfoService;
	@Resource
	private ICouponRuleInfoService couponRuleInfoService;
	@Resource
	private ICodeForFindPassService codeForFindPassService;
	@Resource
	private ICartInfoService cartInfoService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	@Resource
	private IYxjUserCouponGrantConfigService yxjUserCouponGrantConfigService;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		LoginRequestBean requestBean = (LoginRequestBean)baseRequest;
		LoginResponseBean responseBean = new LoginResponseBean();
		BaseResponse responseParam = new BaseResponse();
		String telephone = requestBean.getTelephone();
		
		
		String cartCount = "0";
		String userId=UUIDGenerator.getUUID();
		String memberflag="0";
		String paypassflag="0";
		String useramount="0";
		WeChatUserInfo wxUser = null;
		if(ObjectTools.isNullByString(requestBean.getTelephone())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("手机号不能为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		if("wechat".equals(requestBean.getPublicBean().getOs())){
			if(ObjectTools.isNullByString(requestBean.getOpenid())){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("请从微信端登录");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			//请求的openid是否已绑定过手机号
			wxUser = weChatUserInfoService.getByOpenId(requestBean.getOpenid());
			if( wxUser != null && wxUser.getUser() != null && 
					!requestBean.getTelephone().equals(wxUser.getUser().getTelephone())){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("该微信已绑定其他手机");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
		}
		
		//检查手机号是否被注册
		List<UserInfo> userinfoList = userInfoService.getUserInfoByTelephone(requestBean.getTelephone());
		if(!checkUserType(userinfoList, requestBean)){
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		if(!checkVerifyCode(requestBean)){
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		

		
		if(userinfoList == null 
				|| userinfoList.size() <= 0){
			registerUser(requestBean,userId,responseBean);
			responseBean.setIslogin("0");
		}else{
			
			UserInfo userInfo = userinfoList.get(0);
			userId = userInfo.getUserId();
			if("wechat".equals(requestBean.getPublicBean().getOs())){
				if(wxUser != null && wxUser.getUser() == null){
				    //判断userId是否绑定其他opendId
					if(weChatUserInfoService.getWeChatUserInfoByUserId(userId) != null){
						ErrorEnum.valueOf("FAIL_5555").changetMessage("该手机已绑定其他微信，请核实");
						throw new BaseException(ErrorEnum.FAIL_5555);
					}
					
					this.weChatUserInfoService.update(requestBean.getOpenid(), userId);
				}
			}
			cartCount = cartInfoService.getCartInfoCount(userinfoList.get(0).getUserId());
			memberflag=userInfo.getIsMember();
			if(userInfo.getPayPass() != null){
				paypassflag="1";
			}
			useramount = userInfo.getAmount().toString();
			responseBean.setIslogin("1");
		}
		
		//向responseBean中设置参数
		responseBean.setUserid(userId);	//设置返回的ID
		responseBean.setTelephone(telephone);//设置返回的电话
		responseBean.setCardcount(cartCount);//获得购物车总数
		responseBean.setMemberflag(memberflag);
		responseBean.setPaypassflag(paypassflag);
		responseBean.setUseramount(useramount);
		
		List<CouponUseInfo> couponList = couponUseInfoService.getUsefulCouponList(userId);
		if(couponList != null){
			responseBean.setCouponcount(String.valueOf(couponList.size()));
		}else{
			responseBean.setCouponcount("0");
		}
		
		
		
		responseParam.getParamdata().add(responseBean);

		return responseParam;
	}
	
	private boolean checkUserType(List<UserInfo> userinfoList,LoginRequestBean requestBean){
		if("1".equals(requestBean.getSendchannel())){
			if(userinfoList == null 
					|| userinfoList.size() <= 0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("您暂未注册商城用户");
				return false;
			}else{
				UserInfo userInfo = userinfoList.get(0);
				if("C".equals(userInfo.getUsertype())){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("您的用户类型不正确，不能登录");
					return false;
				}
				
			}
		}else if(!"0".equals(requestBean.getSendchannel())){
			if(userinfoList == null 
					|| userinfoList.size() <= 0){
			}else{
				UserInfo userInfo = userinfoList.get(0);
				if("B".equals(userInfo.getUsertype())){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("您的用户类型不正确，不能登录");
					return false;
				}
				
			}
		}
		
		return true;
	}
	private void registerUser(LoginRequestBean requestBean,String userId,LoginResponseBean responseBean){
		String wetchid = UUIDGenerator.getUUID();
		UserInfo newUser = new UserInfo();
		newUser.setUserId(userId);
		newUser.setTelephone(requestBean.getTelephone());
		newUser.setAmount(new BigDecimal(0));
		newUser.setIsMember("0");
		newUser.setState("1");
		newUser.setMemRebate(new BigDecimal(0));
		newUser.setDeviceNo(requestBean.getPublicBean().getImei());
		newUser.setCreateTime(new Date());
		newUser.setIsfreshman("1");
		newUser.setIsOnecent("0");
		this.userInfoService.insertUser(newUser);
		//绑定opendid和userid
		//int insertWechatId = userInfoService.insertWechatId(wetchid, userId, requestBean.getOpenid());
		this.weChatUserInfoService.update(requestBean.getOpenid(), userId);
		//送代金券
		giveCouponTicket(userId,responseBean);
       
	}
	
	private void giveCouponTicket(String userId,LoginResponseBean responseBean){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("state", "1");
			map.put("userId", userId);
			
			//获取新人注册发放红包配置信息
			YxjUserCouponGrantConfig grantConfig = yxjUserCouponGrantConfigService.findGrantConfig("1");
			
			if(!ObjectTools.isNullByObject(grantConfig)){
				//获取新人注册发放红包配置明细信息
				List<YxjUserCouponGrantConfigDetail> configDetailList = yxjUserCouponGrantConfigService.findListGrantConfigDetail(grantConfig.getConfigId());
	            for(YxjUserCouponGrantConfigDetail configDetail : configDetailList){
	            	CouponRuleInfo couponRule = configDetail.getCouponRuleInfo();
	            	int quantity = configDetail.getGrantQuantity();
	            	for(int i=0;i<quantity;i++){
	            		
	            		map.put("cuid", UUIDGenerator.getUUID());
		            	map.put("couponid", couponRule.getCouponId());
		            	map.put("couponAmount", couponRule.getCouponMoney());
		            	Date today = new Date();
		            	map.put("useBeginTime", DateUtil.toStr(today, "yyyy-MM-dd"));
		            	map.put("useEndTime", DateUtil.toStr(DateUtil.nextSomeDay(today, couponRule.getTimeLength()-1), "yyyy-MM-dd"));
		            	map.put("sinceMoney", couponRule.getSinceMoney());
		                map.put("couponName", couponRule.getCouponName());
		                map.put("couponDesc", couponRule.getCouponDesc());
		                map.put("couponTitle", "新人红包");
		            	couponUseInfoService.insert(map);
	            	}
	            	/*VoucherBean ticketdetail = new VoucherBean();
	            	ticketdetail.setDatebegin(DateUtil.toStr((Date)map.get("useBeginTime"), "yyyy-MM-dd"));
	            	ticketdetail.setDateend(DateUtil.toStr((Date)map.get("useEndTime"), "yyyy-MM-dd"));
	            	ticketdetail.setDescrib((String) map.get("couponDesc"));
	            	ticketdetail.setTicketid((String)map.get("cuid").toString());
	            	ticketdetail.setTickettitle((String)map.get("couponTitle"));
	            	ticketdetail.setValue(String.valueOf((Integer)map.get("CouponAmount")));
	            	
	            	responseBean.setTicketdetail(ticketdetail);*/
	            	
	            	responseBean.setTotalmoney(String.valueOf(grantConfig.getTotalMoney()));
	            }
	           
			}
	}
	
	
	private boolean checkVerifyCode(LoginRequestBean requestBean){
		
		//校验验证码
		List<CodeForFindPass> cffps = codeForFindPassService.getCodeForFindPass(requestBean.getTelephone());
		CodeForFindPass cffp = (cffps != null && cffps.size() > 0) ? cffps.get(0) : null;
		if(ObjectTools.isNullByObject(cffp)){
		    logger.error("用户:" + requestBean.getTelephone() + "验证码失效");
			ErrorEnum.valueOf("FAIL_5555").changetMessage("验证码失效");
			return false;
		}
		if(!cffp.getVerifyCode().equals(requestBean.getVerifycode())){
			//验证码错误
			logger.error("用户:" + requestBean.getTelephone() + "验证码不正确");
			ErrorEnum.valueOf("FAIL_5555").changetMessage("验证码不正确");
			return false;
		}
		
		codeForFindPassService.updataCodeForFindPass(cffp.getId(), "2");
		return true;

	}



}
