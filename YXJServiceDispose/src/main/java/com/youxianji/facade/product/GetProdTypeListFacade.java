package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetProdTypeListRequestBean;
import com.youxianji.facade.product.bean.GetProdTypeListResponseBean;
import com.youxianji.facade.product.bean.json.ProdTypeBean;
import com.youxianji.pojo.ProdType;
import com.youxianji.service.IProdInfoService;

@Facade(command="3002",comment="获取商品类别")
@Scope("prototype")
public class GetProdTypeListFacade extends AbsFacade{
	Logger logger = Logger.getLogger(GetProdTypeListFacade.class);
	@Resource
	private IProdInfoService prodInfoService;

	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetProdTypeListRequestBean requestBean = (GetProdTypeListRequestBean)baseRequest;
		GetProdTypeListResponseBean responseBean = new GetProdTypeListResponseBean();
		BaseResponse responseParam = new BaseResponse();
		String uptypeid = requestBean.getUptypeid();
		List<ProdType> prodTypeList = prodInfoService.getProdTypeList(uptypeid);
		List<ProdTypeBean> typeBeanList = new ArrayList<ProdTypeBean>();
		ProdTypeBean typeBean = null;
		for(ProdType pt:prodTypeList){
			 typeBean = new ProdTypeBean();
			 typeBean.setTypeid(pt.getTypeid());
			 typeBean.setTypelevel(pt.getDepth().toString());
			 typeBean.setTypename(pt.getTypename());
			 typeBeanList.add(typeBean);
		}
		
		responseBean.setTypelist(typeBeanList);
			
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
	}

}
