package com.youxianji.facade.system;

import java.util.ArrayList;
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

import com.youxianji.facade.system.bean.MemberCarResponse;
import com.youxianji.facade.system.bean.MemberCardRequestBean;
import com.youxianji.facade.system.bean.MemberCardResponseBean;
import com.youxianji.pojo.MemberCardInfo;
import com.youxianji.service.IMemberCardInfoService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command = "1014", comment = "会员充值活动接口")
// 自定义标签
@Scope("prototype")
// 标签
public class MemberCardFacade extends AbsFacade {

	@Resource
	private IMemberCardInfoService iMemberCardInfoService;

	private Logger logger = Logger.getLogger(this.getClass());

	public BaseResponse execute(BaseRequest baseRequest) {
		MemberCardResponseBean memberCardResponseBean = new MemberCardResponseBean();
		MemberCardRequestBean memberCardBean = ( MemberCardRequestBean) baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
		List<MemberCardInfo> memberCardInfoList = null;
		if("B".equals(memberCardBean.getCardtype())){
			memberCardInfoList = iMemberCardInfoService.findByCardType("B");
		}else{
			memberCardInfoList = iMemberCardInfoService.findByCardType("C");
		}
		
		if (ObjectTools.isNullByObject(memberCardInfoList)) {
			logger.error("用户:" + memberCardBean.getUserid() + "没有可选代金券面值进行充值");
            throw new BaseException(ErrorEnum.FAIL_1012);
		}
		
		List<MemberCarResponse> membercardlist = new ArrayList< MemberCarResponse>();
		for (MemberCardInfo memberCardInfo : memberCardInfoList) {
			MemberCarResponse memberCarResponse= new  MemberCarResponse();
			memberCarResponse.setCardid(memberCardInfo.getCardid());
			memberCarResponse.setCardvalue(memberCardInfo.getCardvalue()
					.toString());
			memberCarResponse.setRemark(memberCardInfo.getRemark());
			membercardlist.add(memberCarResponse);
	
		}
		
		memberCardResponseBean.setMembercardlist(membercardlist);
		responseParam.getParamdata().add(memberCardResponseBean);
		
		return responseParam;
	}


}
