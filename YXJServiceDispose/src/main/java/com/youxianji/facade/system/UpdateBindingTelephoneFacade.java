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

import com.youxianji.facade.system.bean.UpdateBindingTelephoneRequestBean;
import com.youxianji.pojo.CodeForFindPass;
import com.youxianji.pojo.UserInfo;
import com.youxianji.service.ICodeForFindPassService;
import com.youxianji.service.IUpdateBindingTelephoneService;
import com.youxianji.service.IUserInfoService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="1007",comment="修改绑定手机号业务处理")
@Scope("prototype")
public class UpdateBindingTelephoneFacade extends AbsFacade{
	
	@Resource
	private IUpdateBindingTelephoneService updateBindingTelephoneService;
	@Resource
	private ICodeForFindPassService codeForFindPassService;
	@Resource
	private IUserInfoService userInfoService;
	
	private Logger logger = Logger.getLogger(this.getClass());
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		
		UpdateBindingTelephoneRequestBean requestBean = (UpdateBindingTelephoneRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		// 校验验证码
		List<CodeForFindPass> cffps = codeForFindPassService.getCodeForFindPass(requestBean.getTelephone());
		CodeForFindPass cffp = (cffps != null && cffps.size() > 0) ? cffps.get(0) : null;
		if (ObjectTools.isNullByObject(cffp)) {
			logger.error("用户:" + requestBean.getTelephone() + "验证码失效");
			throw new BaseException(ErrorEnum.FAIL_1010);
		} else if (!cffp.getVerifyCode().equals(requestBean.getVerifycode())) {
			// 验证码错误
			logger.error("用户:" + requestBean.getTelephone() + "验证码不正确");
			throw new BaseException(ErrorEnum.FAIL_1011);
		}else{
			List<UserInfo> userList = userInfoService.getUserInfoByTelephone(requestBean.getNewphone());
			if( userList!= null && userList.size()>0){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("新手机号已经被注册");
				throw new BaseException(ErrorEnum.FAIL_5555);
			}
			
			codeForFindPassService.updataCodeForFindPass(cffp.getId(), "2");
			updateBindingTelephoneService.updateBindingTelephone(requestBean.getUserid(), requestBean.getTelephone(),
					requestBean.getVerifycode(), requestBean.getNewphone());
		}
        return responseParam;
	}
	
}





















