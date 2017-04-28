package com.youxianji.facade.bargain;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.AddAssistBargainInfoRequestBean;
import com.youxianji.facade.bargain.bean.AddAssistBargainInfoResponseBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.WeChatUserInfo;
import com.youxianji.pojo.YxjUserBarginDetail;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginDetailService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="k005",comment="生成帮助砍价记录")
@Scope("prototype")
public class AddAssistBargainInfoFacade extends AbsFacade{
	
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginDetailService yxjUserBarginDetailService ;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		AddAssistBargainInfoRequestBean requestBean = (AddAssistBargainInfoRequestBean)baseRequest;
		AddAssistBargainInfoResponseBean responseBean = new AddAssistBargainInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		String bargainid = requestBean.getBargainid();
		String userid = requestBean.getUserid();
		YxjUserBarginInfo yxjUserBarginInfo = yxjUserBarginInfoService.findBargainInfoById(bargainid);
		YxjUserBarginRules yxjUserBarginRules=yxjUserBarginRulesService.findBargainRulesById(yxjUserBarginInfo.getRulesId());
		
		//获取商品信息
		ProdInfo prodInfo = prodInfoService.getProdInfoById(yxjUserBarginRules.getProdid());
		BigDecimal barginMoney=null;
		BigDecimal valuePrice = prodInfo.getValuePrice();//原价
		BigDecimal rulesMoney = yxjUserBarginRules.getRulesMoney();//单次砍价金额
		BigDecimal totalMoney = yxjUserBarginInfo.getTotalMoney();//累计砍价金额
		BigDecimal leave = valuePrice.subtract(totalMoney);//剩余
		BigDecimal lowestMoney = yxjUserBarginRules.getLowestMoney();
		if(leave.doubleValue()<=lowestMoney.doubleValue()){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("砍价人数已满!");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		String isconcern="";
		WeChatUserInfo weChatUserInfo = weChatUserInfoService.getWeChatUserInfoByUserId(userid);
		if(ObjectTools.isNullByObject(weChatUserInfo)){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("未验证用户信息，请重试!");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		List<YxjUserBarginDetail> barginDetailList=yxjUserBarginDetailService.findBargainDetailByUser(yxjUserBarginInfo.getRulesId(),userid);
		if(barginDetailList.size()>=3){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("每人仅限帮忙砍价三次!");
    		throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		YxjUserBarginDetail yxjUserBarginDetail = yxjUserBarginDetailService.findBargainDetail(bargainid, userid);
		if(yxjUserBarginDetail==null){
			yxjUserBarginDetailService.insertBargainDetail(yxjUserBarginRules.getId(),bargainid, userid, yxjUserBarginInfo,
					rulesMoney, totalMoney, leave,lowestMoney);
		}
		
		responseBean.setIsconcern(isconcern);
		responseParam.getParamdata().add(responseBean);
		return responseParam;
		
	}
	
}
