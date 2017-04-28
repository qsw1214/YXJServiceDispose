package com.youxianji.facade.system;

import static com.youxianji.facade.system.util.FindPassTool.getVerfycode;
import static com.youxianji.facade.system.util.FindPassTool.isVerfycodInvalid;
import static com.youxianji.util.Constants.SMS_PROPERTIES;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.util.UUIDGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.ApplyVerifyRequestBean;
import com.youxianji.network.sms.SmsSendUtil;
import com.youxianji.pojo.CodeForFindPass;
import com.youxianji.pojo.yxj_system_sms_config;
import com.youxianji.service.ICodeForFindPassService;
import com.youxianji.service.IyxjSystemSmsConfigService;
import com.youxianji.util.PropertyManager;
import com.youxianji.web.util.ErrorEnum;
import com.youxianji.web.util.SmsSendType;

@Facade(command="1003",comment="获取短信验证码接口业务处理")
@Scope("prototype")
public class ApplyVerifyFacade extends AbsFacade{
	
	@Resource
	private ICodeForFindPassService codeForFindPassService;
	@Resource
	private IyxjSystemSmsConfigService yxjSystemSmsConfigService;
	
	private Logger logger = Logger.getLogger(this.getClass());

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ApplyVerifyRequestBean requestBean = (ApplyVerifyRequestBean)baseRequest;
		
		BaseResponse responseParam = new BaseResponse();
		
		if(ObjectTools.isNullByString(requestBean.getTelephone())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("手机号不能为空");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		//获取当前手机的有效验证码记录
		List<CodeForFindPass> cffps = codeForFindPassService.getCodeForFindPass(requestBean.getTelephone());
		for(CodeForFindPass cffp : cffps){
			if(isVerfycodInvalid(cffp.getCreateTime())){
			
				//将验证码通过短信发出
				Boolean flag = sendVerifycodeSms(cffp.getVerifyCode(),requestBean.getTelephone());
				if(flag){
					//有效,将验证码通过短信方式发出
					logger.info("短信已经发出");
					//向手机返回成功信息
					return responseParam;
				}else{
					//向手机返回错误信息
					throw new BaseException(ErrorEnum.FAIL_9999);
				}
				
			}else{
					//失效,将验证码信息进行更新
					codeForFindPassService.updataCodeForFindPass(cffp.getId(), "2");
				}
			}
				
			//获取6位验证码并保存
			String verfyCode = getVerfycode();
			codeForFindPassService.saveCodeForFindPass(UUIDGenerator.getUUID(),requestBean.getTelephone(), verfyCode);
				
			//将验证码通过短信发出
			Boolean flag = sendVerifycodeSms(verfyCode,requestBean.getTelephone());
				
			if(flag){
				return responseParam;
			}else{
				//向手机返回错误信息
				throw new BaseException(ErrorEnum.FAIL_9999);
			}
		
	}
	
	private Boolean sendVerifycodeSms(String verfyCode,String telephone){
		logger.info("用户:" + telephone + ",验证码已经提交.");
		
		String content = PropertyManager.instance().getValue(SMS_PROPERTIES, "sms.verify.code", true);
		content = content.replace("@verfyCode@", verfyCode);
		String code = SmsSendType.SMS1003.getCode();
		yxj_system_sms_config smsConfigByPointCode = yxjSystemSmsConfigService.getSmsConfigByPointCode(code);
		String agentCode = "";
		String channel = "";
		if(!ObjectTools.isNullByObject(smsConfigByPointCode)){
			agentCode = smsConfigByPointCode.getSmsChannel().getChannelCode();
			channel = smsConfigByPointCode.getChannelType();
		}
		//发送短信
		SmsSendUtil smsSend = new SmsSendUtil();
		Boolean flag = smsSend.sendSms(agentCode,channel,telephone, content);
		return flag;
	}

}
