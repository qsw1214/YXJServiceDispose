package com.youxianji.facade.product;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetFreshmanProductRequestBean;
import com.youxianji.facade.product.bean.GetFreshmanProductResponseBean;
import com.youxianji.facade.product.bean.json.ProdInfoBean;
import com.youxianji.pojo.ProdInfo;
import com.youxianji.service.IAfternoonteaService;
import com.youxianji.service.IFreshmanProductService;
import com.youxianji.service.IPtShowRelationService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.web.util.ErrorEnum;
@Facade(command="3006",comment="获取活动查询商品列表接口")
@Scope("prototype")
public class GetFreshmanProductFacade extends AbsFacade {
	@Resource
	private IFreshmanProductService freshmanProductService;
	@Resource
	private IAfternoonteaService afternoonteaService;
	@Resource
	private IPtShowRelationService ptShowRelationService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetFreshmanProductResponseBean responseBean = new GetFreshmanProductResponseBean();
		GetFreshmanProductRequestBean requestBean = (GetFreshmanProductRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
        String flag = requestBean.getFlag();
        
        List<ProdInfo> prodList = null;
		List<ProdInfoBean> prodBeanList = new ArrayList<ProdInfoBean>();
        if("1".equals(flag)){
        	prodList = freshmanProductService.getFreshmanProductList();
        }else if("2".equals(flag)){
        	prodList = freshmanProductService.getRecommendProductList(requestBean.getTagid());
        }else if("3".equals(flag)){
        	prodList = freshmanProductService.getProdByTypetList(requestBean.getTypeid());
        }else if("4".equals(flag)){
        	if(ObjectTools.isNullByString(requestBean.getInvitecode())){
        		ErrorEnum.valueOf("FAIL_5555").changetMessage("请输入正确的体验邀请码");
        		throw new BaseException(ErrorEnum.FAIL_5555);
        	}
        	
        	prodList = freshmanProductService.getPennyProductList(requestBean.getInvitecode());
        	
        }
        
        ProdInfoBean prodBean = null;
        if(prodList != null){
			for(ProdInfo pd:prodList){
				prodBean = new ProdInfoBean();
				prodBean.setProdTagsList(ptShowRelationService.getProdTags(pd.getProdid()));
				prodBean.setBuytype(null);
				prodBean.setImageurl(pd.getImageUrl());
				prodBean.setIsactivity(null);
				prodBean.setLimitquantity(null);
				prodBean.setPreselltime(DateUtil.toStr(pd.getPreSellTime(), "yyyy-MM-dd HH:mm:ss"));
				prodBean.setProdname(pd.getProdname());
				prodBean.setProddesc(pd.getProdDesc());
				prodBean.setProdid(pd.getProdid());
				prodBean.setSellflag(pd.getPreSellTag());
				prodBean.setUnit(pd.getProdUnit());
				prodBean.setValueprice(pd.getValuePrice().toString());
				prodBean.setSellprice(pd.getSellPrice().toString());
				prodBean.setFreshflag(pd.getIsfreshman());
				prodBean.setCpackage(pd.getCpackage());
				prodBean.setProdstock(pd.getProdstock());
				
				prodBean.setDetailurl(Constants.PROD_DETAIL_URL.replace("PRODID", pd.getProdid()));
				if("1".equals(pd.getIsfreshman())){
					prodBean.setFreshprice(pd.getFreshmanprice().toString());
				}
	
				prodBeanList.add(prodBean);
			}
        }	
		responseBean.setProdlist(prodBeanList);
		 
		
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
	}
	
	
	

}



















