package com.youxianji.facade.bargain;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.GetUserLaunchBargainInfoRequestBean;
import com.youxianji.facade.bargain.bean.GetUserLaunchBargainInfoResponseBean;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command = "k001", comment = "获取用户发起砍价信息")
@Scope("prototype")
public class GetUserLaunchBargainInfoFacade extends AbsFacade {
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IOrderInfoService orderInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetUserLaunchBargainInfoRequestBean requestBean = (GetUserLaunchBargainInfoRequestBean) baseRequest;
		GetUserLaunchBargainInfoResponseBean responseBean = new GetUserLaunchBargainInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();

			// 是否存在活动0.不存在 1.存在
			String isenable = "1";
			YxjUserBarginRules yxjUserBarginRules = yxjUserBarginRulesService
					.getBargainRulesByDate();
			if (yxjUserBarginRules == null) {
				ErrorEnum.valueOf("FAIL_5555").changetMessage("该活动已结束!");
			    throw new BaseException(ErrorEnum.FAIL_5555);
			}
			String userid = "";
			String telephone = "";
			String isregister = "1";
			String ismakebargain = "0";
			String bargainid = "";
			String isorder = "0";
			WeChatUserInfo weChatUserInfo = weChatUserInfoService
					.getByOpenId(requestBean.getOpenid());
			if (weChatUserInfo == null) {
				isregister = "0";
			} else {
				UserInfo userInfo = weChatUserInfo.getUser();
				// 判断是否注册0.未注册 1.已注册
				if (userInfo == null) {
					isregister = "0";
				} else {
					userid = userInfo.getUserId();
					telephone = userInfo.getTelephone();
					// 是否发起砍价 0.未发起 1.已发起
					YxjUserBarginInfo yxjUserBarginInfo = yxjUserBarginInfoService
							.findBargainInfoByUserId(
									yxjUserBarginRules.getId(), userid);
					if (yxjUserBarginInfo != null) {
						ismakebargain = "1";
						bargainid = yxjUserBarginInfo.getId();
					}
					// 是否下单过
					List<OrderInfo> obarginOrderList = orderInfoService
							.getBarginInfoList(userid);
					if (obarginOrderList != null && obarginOrderList.size() > 0) {
						for (OrderInfo oi : obarginOrderList) {
							if (oi.getPaytime() != null
									&& oi.getPaytime().compareTo(
											yxjUserBarginRules.getBegintime()) >= 0
									&& oi.getPaytime().compareTo(
											yxjUserBarginRules.getEndtime()) <= 0) {
								isorder = "1";
							}
						}
					}
				}
			}

			responseBean.setIsregister(isregister);
			responseBean.setIsenable(isenable);
			responseBean.setIsmakebargain(ismakebargain);
			responseBean.setIsorder(isorder);
			responseBean.setUserid(userid);
			responseBean.setTelephone(telephone);
			responseBean.setBargainid(bargainid);

			responseParam.getParamdata().add(responseBean);
			return responseParam;
	}

}
