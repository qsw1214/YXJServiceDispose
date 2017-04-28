package com.youxianji.facade.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.ConfigPayPassRequestBean;
import com.youxianji.facade.system.bean.LoginResponseBean;
import com.youxianji.pojo.CodeForFindPass;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ICodeForFindPassService;
import com.youxianji.service.IPayPasswordservice;
import com.youxianji.service.IUserInfoService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="1004",comment="设置支付密码业务处理")
@Scope("prototype")
public class ConfigPayPassFacade extends AbsFacade{
	@Resource
	private IUserInfoService userInfoService;
	@Resource
	private IPayPasswordservice payPasswordservice;
	@Resource
	private ICodeForFindPassService codeForFindPassService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ConfigPayPassRequestBean requestBean = (ConfigPayPassRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		LoginResponseBean responseBean = new LoginResponseBean();
		
			// 校验验证码
			List<CodeForFindPass> cffps = codeForFindPassService.getCodeForFindPass(requestBean.getTelephone());
			CodeForFindPass cffp = (cffps != null && cffps.size() > 0) ? cffps.get(0) : null;
			if (ObjectTools.isNullByObject(cffp)) {
				logger.error("用户:" + requestBean.getTelephone() + "验证码失效");
				throw new BaseException(ErrorEnum.FAIL_1010);
			}else if (!cffp.getVerifyCode().equals(requestBean.getVerifycode())) {
				// 验证码错误
				logger.error("用户:" + requestBean.getTelephone() + "验证码不正确");
				throw new BaseException(ErrorEnum.FAIL_1011);
			}
			
		  List<UserInfo> userList =  userInfoService.getUserInfoByTelephone(requestBean.getTelephone());
          if(userList == null || userList.size() <= 0){
        	   ErrorEnum.valueOf("FAIL_5555").changetMessage("输入的手机号在系统中不存在");
        	   throw new BaseException(ErrorEnum.FAIL_5555);
		  }
          
          if(!userList.get(0).getTelephone().equals(requestBean.getTelephone())){
        	  ErrorEnum.valueOf("FAIL_5555").changetMessage("输入的手机号和用户注册手机号不一致");
       	      throw new BaseException(ErrorEnum.FAIL_5555);
          }
          
	
		 payPasswordservice.updateUserPayPassword(requestBean.getUserid(),requestBean.getPassword(), requestBean.getTelephone(), requestBean.getVerifycode());
			
	     codeForFindPassService.updataCodeForFindPass(cffp.getId(), "2");	
			
		// 向responseBean中设置参数
		responseBean.setUserid(requestBean.getUserid()); // 设置返回的ID
		responseBean.setTelephone(requestBean.getTelephone()); // 设置返回的电话
		 
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}

}





























