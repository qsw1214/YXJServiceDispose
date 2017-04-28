package com.youxianji.facade.groupbuy.bean;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;

@InterfaceParam(command="3014",comment="获取社区团购商品列表")
@Scope("prototype")
public class GetGroupbuyProdInfoRequestBean extends BaseRequest{
	
}
