package com.youxianji.web.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import base.cn.exception.BaseException;
import base.cn.web.facade.IFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.util.GsonTools;
import com.youxianji.web.util.ErrorEnum;
import com.youxianji.web.util.MobileResponse;

@Controller
@RequestMapping("/mobile")
@Scope("prototype")
public class MobileController {
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@RequestMapping(value = "/serverControl")
	@ResponseBody
	public BaseResponse serverControl(HttpServletRequest request,HttpServletResponse response){
		
		BaseResponse baseResponse = new BaseResponse();
		BaseRequest baseRequest = (BaseRequest) request.getAttribute("baseRequest");
		try{
			IFacade facade = (IFacade)request.getAttribute("facade");
			baseResponse = facade.execute(baseRequest);
			if(baseResponse != null){
				baseResponse.setSign(MobileResponse.getSignByOs(baseRequest.getPublicBean().getOs(), baseResponse));
				logger.info("接口"+baseRequest.getPublicBean().getCommand()+"返回内容:"+GsonTools.getJsonString(baseResponse));
			}
		}catch(BaseException e){
			baseResponse.setSuccess("false");
			baseResponse.setReturncode(e.getError().getCode());
			baseResponse.setReturnmessage(e.getError().getMessage());
			logger.info("接口"+baseRequest.getPublicBean().getCommand()+"异常消息."+GsonTools.getJsonString(baseResponse));
			return baseResponse;
		}catch(Exception e){
			baseResponse.setSuccess("false");
			baseResponse.setReturncode(ErrorEnum.FAIL_9999.getCode());
			baseResponse.setReturnmessage(ErrorEnum.FAIL_9999.getMessage());
			logger.error("手机端业务未知处理异常.", e);
			return baseResponse;
		}
		
		return baseResponse;
	}

}
