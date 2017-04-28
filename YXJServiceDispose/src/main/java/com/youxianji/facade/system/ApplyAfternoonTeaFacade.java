package com.youxianji.facade.system;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.util.UUIDGenerator;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.ApplyAfternoonTeaRequestBean;
import com.youxianji.pojo.Afternoontea;
import com.youxianji.service.IAfternoonteaService;

@Facade(command="1015",comment="下午茶申请接口")
@Scope("prototype")
public class ApplyAfternoonTeaFacade  extends AbsFacade{
	
	@Resource
	private IAfternoonteaService afternoonteaService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		
		ApplyAfternoonTeaRequestBean requestBean = (ApplyAfternoonTeaRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		Afternoontea tea = new Afternoontea();
		tea.setCompanyname(requestBean.getCompanyname());
		tea.setContractpeople(requestBean.getContractpeople());
		tea.setEmployquantity(Integer.valueOf(requestBean.getEmployquantity()));
		tea.setId(UUIDGenerator.getUUID());
		//tea.setInvitecode(ShortUUID.generateShortUuid());
		tea.setPosition(requestBean.getPosition());
		tea.setTelephone(requestBean.getTelephone());
		
		afternoonteaService.insert(tea);
		
		return responseParam;
	}

}













