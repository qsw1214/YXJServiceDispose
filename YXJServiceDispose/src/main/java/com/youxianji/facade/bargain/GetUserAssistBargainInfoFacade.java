package com.youxianji.facade.bargain;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.GetUserAssistBargainInfoRequestBean;
import com.youxianji.facade.bargain.bean.GetUserAssistBargainInfoResponseBean;
import com.youxianji.facade.bargain.bean.json.AssistbargainBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginDetailService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;

@Facade(command = "k002", comment = "获取用户帮忙砍价信息")
@Scope("prototype")
public class GetUserAssistBargainInfoFacade extends AbsFacade {
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginDetailService yxjUserBarginDetailService;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IProdInfoService prodInfoService;

	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetUserAssistBargainInfoRequestBean requestBean = (GetUserAssistBargainInfoRequestBean) baseRequest;
		GetUserAssistBargainInfoResponseBean responseBean = new GetUserAssistBargainInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();

		String bargainid = requestBean.getBargainid();
		YxjUserBarginInfo yxjUserBarginInfo = yxjUserBarginInfoService.findBargainInfoById(bargainid);
		List<AssistbargainBean> assistbargainlist = yxjUserBarginDetailService.findUserAssistBargainInfoList(yxjUserBarginInfo.getId());
		YxjUserBarginRules yxjUserBarginRules = yxjUserBarginRulesService.findBargainRulesById(yxjUserBarginInfo.getRulesId());
		ProdInfo prodInfo = prodInfoService.getProdInfoById(yxjUserBarginRules.getProdid());
		BigDecimal valuePrice = prodInfo.getValuePrice();// 原价
		BigDecimal totalMoney = yxjUserBarginInfo.getTotalMoney();// 累计砍价金额
		BigDecimal leave = valuePrice.subtract(totalMoney);// 剩余
		responseBean.setAssistbargainlist(assistbargainlist);
		responseBean.setLeaveMoney(leave.toString());
		responseParam.getParamdata().add(responseBean);
		return responseParam;
	}

}
