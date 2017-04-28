package com.youxianji.facade.yxjia;
import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
import base.cn.web.facade.bean.PublicBean;

import com.youxianji.facade.yxjia.bean.JIAGetNewVersionRequestBean;
import com.youxianji.facade.yxjia.bean.JIAGetNewVersionResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIANewAppInfoData;
import com.youxianji.pojo.YxjBuserAppversionInfo;
import com.youxianji.service.IYxjBuserAppversionInfoService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="5001",comment="JIA-获取新版本信息接口")
@Scope("prototype")
public class JIAGetNewVersionFacade extends AbsFacade{
	Logger logger = Logger.getLogger(JIAGetNewVersionFacade.class);
	@Resource
	private IYxjBuserAppversionInfoService appVersionService;
	

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		JIAGetNewVersionRequestBean requestBean = (JIAGetNewVersionRequestBean)baseRequest;
		JIAGetNewVersionResponseBean responseBean = new JIAGetNewVersionResponseBean();
		BaseResponse responseParam = new BaseResponse();

		
		String curVersionNum = baseRequest.getPublicBean().getApp_version();
		logger.info("生活半小时商家后台请求参数版本号："+curVersionNum);
		if(ObjectTools.isNullByString(curVersionNum)){
			logger.error("APP应用版本不能为空.");
			throw new BaseException(ErrorEnum.FAIL_9001);
		}
		String os=null;
		//判断是否是ios应用
		PublicBean publicBean= baseRequest.getPublicBean();
		if("ios".equals(publicBean.getOs())){
			os="ios";
		}else{
			os="android";
		}
		
		YxjBuserAppversionInfo newAppInfo = appVersionService.getNewAppDeliverInfo(curVersionNum,os);
		
		//判断是否有新版本
		if(ObjectTools.isNullByObject(newAppInfo)){
			responseBean.setData(new JIANewAppInfoData());
			logger.info("没有最新版本");
			responseParam.getParamdata().add(responseBean);
			return responseParam;
		}
		logger.info("最新版本号："+newAppInfo.getBuserAppVersionNum());
		JIANewAppInfoData data = new JIANewAppInfoData();
		data.setHasnewversion(true);
		data.setNewversionnum(newAppInfo.getBuserAppVersionNum());
		data.setNewdownpath(newAppInfo.getBuserAppDownPath());
		data.setContent(newAppInfo.getBuserAppContent());
		responseBean.setData(data);
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}
