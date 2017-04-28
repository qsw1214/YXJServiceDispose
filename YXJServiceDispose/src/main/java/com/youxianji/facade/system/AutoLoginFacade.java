package com.youxianji.facade.system;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.AutoLoginRequestBean;
import com.youxianji.facade.system.bean.AutoLoginResponseBean;
import com.youxianji.pojo.CouponUseInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserEmployee;
import com.youxianji.service.ICartInfoService;
import com.youxianji.service.ICouponUseInfoService;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserEmployeeService;
import com.youxianji.web.util.ErrorEnum;
@Facade(command="1001",comment="自动登录接口业务处理")	//自定义标签
@Scope("prototype")
public class AutoLoginFacade extends AbsFacade {
	
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private ICartInfoService cartInfoService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	@Resource
	private ICouponUseInfoService couponUseInfoService;
	@Resource
	private IYxjUserEmployeeService yxjUserEmployeeService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		
		AutoLoginResponseBean responseBean = new AutoLoginResponseBean();
		AutoLoginRequestBean requestBean = (AutoLoginRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();

		
		if(ObjectTools.isNullByString(requestBean.getOpenid())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("数据有误，请重新进入商城");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		//请求的openid是否已绑定过手机号
		WeChatUserInfo wxUser = weChatUserInfoService.getByOpenId(requestBean.getOpenid());
		if(wxUser != null && wxUser.getUser() != null ){
			UserInfo userinfo = userInfoService.getByUserId(wxUser.getUser().getUserId());
			if(userinfo != null){
				
				if("B".equals(userinfo.getUsertype()) && !"0".equals(requestBean.getSendchannel())){
					ErrorEnum.valueOf("FAIL_5555").changetMessage("您的用户类型不正确，不能登录");
					throw new BaseException(ErrorEnum.FAIL_5555);
				}
				
				//向responseBean中设置参数
				responseBean.setUserid(userinfo.getUserId()); // 设置返回的ID
				responseBean.setTelephone(userinfo.getTelephone()); // 设置返回的电话
				responseBean.setCardcount(cartInfoService.getCartInfoCount(userinfo.getUserId())); // 获得购物车总数
				String paypassflag="0";
				if(userinfo.getPayPass() != null){
					paypassflag="1";
				}
				
				responseBean.setMemberflag(userinfo.getIsMember());
				responseBean.setPaypassflag(paypassflag);
				responseBean.setUseramount(userinfo.getAmount() == null?"0":userinfo.getAmount().toString());
				List<CouponUseInfo> couponList = couponUseInfoService.getUsefulCouponList(userinfo.getUserId());
				if(couponList != null){
					responseBean.setCouponcount(String.valueOf(couponList.size()));
				}else{
					responseBean.setCouponcount("0");
				}
				List<YxjUserEmployee> userEmployeeList= yxjUserEmployeeService.getUserEmployeeByTelephone(userinfo.getTelephone());
				if(userEmployeeList!=null&&userEmployeeList.size()>0){
					YxjUserEmployee yxjUserEmployee=userEmployeeList.get(0);
					responseBean.setInvitecode(yxjUserEmployee.getEmploynum());
				}
			}
			
		}
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
