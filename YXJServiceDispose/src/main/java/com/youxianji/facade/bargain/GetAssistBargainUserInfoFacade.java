package com.youxianji.facade.bargain;

import java.math.BigDecimal;
import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.GetAssistBargainUserInfoRequestBean;
import com.youxianji.facade.bargain.bean.GetAssistBargainUserInfoResponseBean;
import com.youxianji.facade.bargain.bean.json.AssistbargainBean;
import com.youxianji.pojo.OrderInfo;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserBarginDetail;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IOrderInfoService;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginDetailService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;
import com.youxianji.util.Base64Util;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="k003",comment="获取协助砍价人信息列表")
@Scope("prototype")
public class GetAssistBargainUserInfoFacade extends AbsFacade{
	
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginDetailService yxjUserBarginDetailService ;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IOrderInfoService orderInfoService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetAssistBargainUserInfoRequestBean requestBean = (GetAssistBargainUserInfoRequestBean)baseRequest;
		GetAssistBargainUserInfoResponseBean responseBean = new GetAssistBargainUserInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();

		String bargainid=requestBean.getBargainid();
		String userid=requestBean.getUserid();
		YxjUserBarginInfo yxjUserBarginInfo =yxjUserBarginInfoService.findBargainInfoById(bargainid);
		//获取发起砍价人头像和昵称
		WeChatUserInfo weChatUserInfo = weChatUserInfoService.getWeChatUserInfoByUserId(yxjUserBarginInfo.getUserId());
		if(weChatUserInfo != null){
			responseBean.setHeadimgurl(weChatUserInfo.getHeadimgurl());
			String regExp = "^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{4}|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)$";  
	        Pattern p = Pattern.compile(regExp);  
	        if(weChatUserInfo.getNickname() != null){
	        	if(p.matcher(weChatUserInfo.getNickname()).matches()){
		        	Base64Util base64Util = new Base64Util();
		        	responseBean.setNickname(base64Util.Decode(weChatUserInfo.getNickname()));
		        }else{
		        	responseBean.setNickname(weChatUserInfo.getNickname());
		        }
	        }else{
	        	responseBean.setNickname("");
	        }
		}
		YxjUserBarginRules yxjUserBarginRules=yxjUserBarginRulesService.findBargainRulesById(yxjUserBarginInfo.getRulesId());
		if(yxjUserBarginRules == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该活动已结束!");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		String isassistbargain="0";
		List<YxjUserBarginDetail> barginDetailList=yxjUserBarginDetailService.findBargainDetailByBargainId(bargainid);
		for (YxjUserBarginDetail yxjUserBarginDetail : barginDetailList) {
			String userId = yxjUserBarginDetail.getRelationUserId();
			if(userId.equals(userid)){
				isassistbargain="1";
			}
		}
		//是否下单过
		List<OrderInfo> obarginOrderList = orderInfoService.getBarginInfoList(yxjUserBarginInfo.getUserId());
		if(obarginOrderList!= null && obarginOrderList.size()>0){
			for(OrderInfo oi:obarginOrderList){
				if(oi.getPaytime() != null 
						&& oi.getPaytime().compareTo(yxjUserBarginRules.getBegintime()) >= 0
						&& oi.getPaytime().compareTo(yxjUserBarginRules.getEndtime()) <= 0){
					isassistbargain="2";
				}
			}
		}
		String ismakebargain="0";
		if(yxjUserBarginInfo.getUserId().equals(userid)){
			ismakebargain="2";
		}else{
			YxjUserBarginInfo barginInfo=yxjUserBarginInfoService.findBargainInfoByUserId(yxjUserBarginRules.getId(),userid);
			if(barginInfo!=null){
					ismakebargain="1";
			}
		}
		List<AssistbargainBean> assistbargainlist=yxjUserBarginDetailService.findAssistbargainList(yxjUserBarginInfo.getId());
		ProdInfo prodInfo = prodInfoService.getProdInfoById(yxjUserBarginRules.getProdid());
		BigDecimal valuePrice = prodInfo.getValuePrice();//原价
		BigDecimal totalMoney = yxjUserBarginInfo.getTotalMoney();//累计砍价金额
		BigDecimal leave = valuePrice.subtract(totalMoney);//剩余
		responseBean.setAssistbargainlist(assistbargainlist);
		responseBean.setLeaveMoney(leave.toString());
		responseBean.setIsassistbargain(isassistbargain);
		responseBean.setIsmakebargain(ismakebargain);
		responseParam.getParamdata().add(responseBean);
		return responseParam;
		
	}
	
}
