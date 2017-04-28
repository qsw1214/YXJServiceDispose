package com.youxianji.facade.bargain;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.AddLaunchBargainInfoRequestBean;
import com.youxianji.facade.bargain.bean.AddLaunchBargainInfoResponseBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.YxjUserBarginInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;

@Facade(command="k004",comment="生成发起砍价记录")
@Scope("prototype")
public class AddLaunchBargainInfoFacade extends AbsFacade{
	
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IProdInfoService prodInfoService;
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		AddLaunchBargainInfoRequestBean requestBean = (AddLaunchBargainInfoRequestBean)baseRequest;
		AddLaunchBargainInfoResponseBean responseBean = new AddLaunchBargainInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();
		//获取砍价规则
		YxjUserBarginRules yxjUserBarginRules=yxjUserBarginRulesService.getBargainRulesByDate();
		//获取商品信息
		ProdInfo prodInfo = prodInfoService.getProdInfoById(yxjUserBarginRules.getProdid());
		String userid = requestBean.getUserid();
		String bargainid="";
		String isconcern="";
		/*String isconcern="0";//未关注
		WeChatUserInfo weChatUserInfo = weChatUserInfoService.getWeChatUserInfoByUserId(userid);
		if(weChatUserInfo.getIsunsubscribe().equals("0")&&weChatUserInfo.getSubscribetime()!=null){
			isconcern="1";//已关注
			bargainid=yxjUserBarginInfoService.insertBargainInfo(yxjUserBarginRules.getId(),userid,prodInfo.getRemark());
		}*/
		
		YxjUserBarginInfo yxjUserBarginInfo=yxjUserBarginInfoService.findBargainInfoByUserId(yxjUserBarginRules.getId(),userid);
		if(yxjUserBarginInfo!=null){
			bargainid=yxjUserBarginInfo.getId();
		}else{
			bargainid=yxjUserBarginInfoService.insertBargainInfo(yxjUserBarginRules.getId(),userid,prodInfo.getRemark());
		}
		responseBean.setBargainid(bargainid);
		responseBean.setIsconcern(isconcern);
		responseParam.getParamdata().add(responseBean);
		return responseParam;
	}
	
}
