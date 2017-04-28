package com.youxianji.facade.bargain.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="k007",comment="获取砍价商品信息")
@Scope("prototype")
public class GetBargainProdInfoRequestBean extends BaseRequest{
	
}
