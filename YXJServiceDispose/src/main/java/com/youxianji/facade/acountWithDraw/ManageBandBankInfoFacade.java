package com.youxianji.facade.acountWithDraw;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import com.youxianji.facade.acountWithDraw.bean.ManageBandBankRequestBean;
import com.youxianji.facade.acountWithDraw.bean.ManageBandBankResponseBean;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserWithDrawcashBankInfo;
import com.youxianji.pojo.YxjBuserWithdrawcashBandbank;
import com.youxianji.service.IUserInfoService;
import com.youxianji.service.IYxjBuserWithDrawcashBankInfoService;
import com.youxianji.service.IYxjBuserWithdrawcashBandbankService;
import com.youxianji.util.IdcardUtils;
import com.youxianji.web.util.CommonMobileResponse;
import com.youxianji.web.util.ErrorEnum;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.UUIDGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;
@Facade(command="c004",comment="管理提现银行卡接口")
@Scope("prototype")
public class ManageBandBankInfoFacade extends AbsFacade {
	@Resource
	private IYxjBuserWithdrawcashBandbankService yxjBuserWithdrawcashBandbankService;
	@Resource
	private IYxjBuserWithDrawcashBankInfoService yxjBuserWithDrawcashBankInfoService;
	@Resource
	private IUserInfoService userInfoService;
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		ManageBandBankResponseBean responseBean = new ManageBandBankResponseBean();
		ManageBandBankRequestBean requestBean  = (ManageBandBankRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		YxjBuserWithdrawcashBandbank bandbankInfo = new YxjBuserWithdrawcashBandbank();
		YxjBuserWithDrawcashBankInfo bankInfo = yxjBuserWithDrawcashBankInfoService.getBankInfo(requestBean.getBankcode());
		UserInfo dbUserInfo = userInfoService.getByUserId(requestBean.getUserid());
		
		String regex1 = "^[1]+[3,5]+\\d{9}$";//手机号
		String regex2 = "(\\d{14}[0-9a-zA-Z])|(\\d{17}[0-9a-zA-Z])";//身份证号
		String regex3 = "^[A-Za-z0-9]+$";//字符和数字组成的字符串
		
		if("1".equals(requestBean.getConfigflag())){
			YxjBuserWithdrawcashBandbank bandbankInfoHave = yxjBuserWithdrawcashBandbankService.getBandbankHave(requestBean.getUserid());
			if(bandbankInfoHave != null){
				ErrorEnum.valueOf("FAIL_5555").changetMessage("该用户已经绑定过银行卡，不能重复绑卡");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
	    		
			}
			//添加
			if(requestBean.getAccountname() != null && !"".equals(requestBean.getAccountname())){
				bandbankInfo.setBandAccountname(requestBean.getAccountname());
			}else{
				ErrorEnum.valueOf("FAIL_5555").changetMessage("银行账户名不能为空");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
	    		
			}
			if(requestBean.getAccountno() != null && !"".equals(requestBean.getAccountno())){
				if(match(regex3, requestBean.getAccountno())){
					bandbankInfo.setBandCardno(requestBean.getAccountno());
				}else{
					ErrorEnum.valueOf("FAIL_5555").changetMessage("银行卡号不符合规范");
		    		throw new BaseException(ErrorEnum.FAIL_5555);
		    		
				}
			}else{
				ErrorEnum.valueOf("FAIL_5555").changetMessage("银行卡号不能为空");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
	    		
			}
			if(bankInfo != null){
				bandbankInfo.setBankInfo(bankInfo);
			}
			bandbankInfo.setBandBankname(requestBean.getBankname());
			if(requestBean.getIdencard() != null && !"".equals(requestBean.getIdencard())){
				if(IdcardUtils.validateCard(requestBean.getIdencard())){
					bandbankInfo.setBandIdencard(requestBean.getIdencard());
				}else{
					ErrorEnum.valueOf("FAIL_5555").changetMessage("身份证号不符合规范");
		    		throw new BaseException(ErrorEnum.FAIL_5555);
		    		
				}
			}else{
				ErrorEnum.valueOf("FAIL_5555").changetMessage("身份证号不能为空");
	    		throw new BaseException(ErrorEnum.FAIL_5555);
	    		
			}
			bandbankInfo.setUserinfo(dbUserInfo);
			bandbankInfo.setBandid(UUIDGenerator.getUUID());
			bandbankInfo.setBandLimitamount(new BigDecimal(500));
			bandbankInfo.setBandPoundage(new BigDecimal(2));
			yxjBuserWithdrawcashBandbankService.saveBandBank(bandbankInfo);
			//responseParam.getParamdata().add(responseBean);
		}else if("2".equals(requestBean.getConfigflag())){
			//修改
			if(requestBean.getBandid() != null && !"".equals(requestBean.getBandid())){
				bandbankInfo.setBandid(requestBean.getBandid());
				YxjBuserWithdrawcashBandbank dbBandbankInfo = yxjBuserWithdrawcashBandbankService.getBandbankInfoById(requestBean.getBandid());
				if(requestBean.getAccountname() != null && !"".equals(requestBean.getAccountname())){
					dbBandbankInfo.setBandAccountname(requestBean.getAccountname());
				}else{
					ErrorEnum.valueOf("FAIL_5555").changetMessage("银行账户名不能为空");
		    		throw new BaseException(ErrorEnum.FAIL_5555);
		    		
				}
				if(requestBean.getAccountno() != null && !"".equals(requestBean.getAccountno())){
					if(match(regex3, requestBean.getAccountno())){
						dbBandbankInfo.setBandCardno(requestBean.getAccountno());
					}else{
						ErrorEnum.valueOf("FAIL_5555").changetMessage("银行卡号不符合规范");
			    		throw new BaseException(ErrorEnum.FAIL_5555);
			    		
					}
				}else{
					ErrorEnum.valueOf("FAIL_5555").changetMessage("银行卡号不能为空");
		    		throw new BaseException(ErrorEnum.FAIL_5555);
		    		
				}
				
				if(bankInfo != null){
					dbBandbankInfo.setBankInfo(bankInfo);
				}
				dbBandbankInfo.setBandBankname(requestBean.getBankname());
				if(requestBean.getIdencard() != null && !"".equals(requestBean.getIdencard())){
					if(match(regex2, requestBean.getIdencard())){
						dbBandbankInfo.setBandIdencard(requestBean.getIdencard());
					}else{
						ErrorEnum.valueOf("FAIL_5555").changetMessage("身份证号不符合规范");
			    		throw new BaseException(ErrorEnum.FAIL_5555);
			    		
					}
				}else{
					ErrorEnum.valueOf("FAIL_5555").changetMessage("身份证号不能为空");
		    		throw new BaseException(ErrorEnum.FAIL_5555);
		    		
				}
				dbBandbankInfo.setUserinfo(dbUserInfo);
				yxjBuserWithdrawcashBandbankService.updateBandBank(dbBandbankInfo);
				//responseParam.getParamdata().add(responseBean);
			}
		}else{
			ErrorEnum.valueOf("FAIL_5555").changetMessage("设置标记为空");
    		throw new BaseException(ErrorEnum.FAIL_5555);
    		
		}
		return responseParam;
	}

	private boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
		}
}
