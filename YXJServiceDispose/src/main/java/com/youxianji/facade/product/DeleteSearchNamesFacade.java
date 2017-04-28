package com.youxianji.facade.product;
import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.DeleteSearchNamesRequestBean;
import com.youxianji.service.IHistorySeekService;

@Facade(command="3016",comment="删除关键字列表")
@Scope("prototype")
public class DeleteSearchNamesFacade extends AbsFacade {
	@Resource
	private IHistorySeekService historySeekService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		DeleteSearchNamesRequestBean requestBean = (DeleteSearchNamesRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String userId = requestBean.getUserid();
		//构建历史搜索名称
		if(!ObjectTools.isNullByString(userId)){
			historySeekService.deleteHistorySeek(userId);
		}
		
		return responseParam;
	}

}




