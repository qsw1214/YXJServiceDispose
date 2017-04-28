package com.youxianji.facade.bargain;

import java.text.SimpleDateFormat;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.bargain.bean.GetBargainProdInfoRequestBean;
import com.youxianji.facade.bargain.bean.GetBargainProdInfoResponseBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.pojo.YxjUserBarginRules;
import com.youxianji.service.IProdInfoService;
import com.youxianji.service.IWeChatUserInfoService;
import com.youxianji.service.IYxjUserBarginInfoService;
import com.youxianji.service.IYxjUserBarginRulesService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="k007",comment="获取砍价商品信息")
@Scope("prototype")
public class GetBargainProdInfoFacade extends AbsFacade{
	@Resource
	private IWeChatUserInfoService weChatUserInfoService;
	@Resource
	private IYxjUserBarginInfoService yxjUserBarginInfoService;
	@Resource
	private IYxjUserBarginRulesService yxjUserBarginRulesService;
	@Resource
	private IProdInfoService prodInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetBargainProdInfoRequestBean requestBean = (GetBargainProdInfoRequestBean)baseRequest;
		GetBargainProdInfoResponseBean responseBean = new GetBargainProdInfoResponseBean();
		BaseResponse responseParam = new BaseResponse();
		
		//是否存在活动0.不存在 1.存在
		String isenable="1";
		YxjUserBarginRules yxjUserBarginRules=yxjUserBarginRulesService.getBargainRulesByDate();
		if(yxjUserBarginRules == null){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("该活动已结束!");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}else{
			//砍价标题
			responseBean.setRulesTitle(yxjUserBarginRules.getRulesTitle());
			//砍价描述
			responseBean.setRulesDesc(yxjUserBarginRules.getRulesDesc());
			responseBean.setImageUrl(yxjUserBarginRules.getRulesImageurl());
			responseBean.setLowestmoney(yxjUserBarginRules.getLowestMoney().toString());
		}
		//获取砍价商品
		ProdInfo prodInfo = prodInfoService.getProdInfoById(yxjUserBarginRules.getProdid());
		responseBean.setIsenable(isenable);
		responseBean.setProdid(prodInfo.getProdid());
		responseBean.setProdname(prodInfo.getProdname());
		responseBean.setProdstock(prodInfo.getProdstock().toString());
		responseBean.setValuePrice(prodInfo.getValuePrice().toString());
		responseBean.setRulesMoney(yxjUserBarginRules.getRulesMoney().toString());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String endTime = format.format(yxjUserBarginRules.getEndtime());
		responseBean.setEndTime(endTime);
		
		responseParam.getParamdata().add(responseBean);
		return responseParam;
	}
	
}
