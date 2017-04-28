package com.youxianji.facade.system.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="1016",comment="实体卡充值接口")
public class EntityCardChargeRequestBean extends BaseRequest {
	
	private String entitypass;

	public String getEntitypass() {
		return entitypass;
	}

	public void setEntitypass(String entitypass) {
		this.entitypass = entitypass;
	}

	
	
	
}
