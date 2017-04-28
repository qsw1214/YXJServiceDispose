package com.youxianji.facade.system;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.util.UUIDGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.AdviceSuggestionRequestBean;
import com.youxianji.service.IAdviceSuggestionService;

@Facade(command="1008",comment="意见建议业务处理")
@Scope("prototype")
public class AdviceSuggestionFacade  extends AbsFacade{
	
	@Resource
	private IAdviceSuggestionService adviceSuggestionService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		
		AdviceSuggestionRequestBean requestBean = (AdviceSuggestionRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		SimpleDateFormat dfs = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		adviceSuggestionService.insertAdvice(UUIDGenerator.getUUID(),requestBean.getUserid(), requestBean.getContent(), requestBean.getContact(), dfs.format(new Date()));

		return responseParam;
	}

}













