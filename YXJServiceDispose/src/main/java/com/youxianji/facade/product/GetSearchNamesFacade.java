package com.youxianji.facade.product;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.util.ObjectTools;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.product.bean.GetSearchNamesRequestBean;
import com.youxianji.facade.product.bean.GetSearchNamesResponseBean;
import com.youxianji.facade.product.bean.json.HistroyNameBean;
import com.youxianji.facade.product.bean.json.RecommendNameBean;
import com.youxianji.pojo.HistorySeek;
import com.youxianji.pojo.Recommend;
import com.youxianji.service.IHistorySeekService;
import com.youxianji.service.IRecommendService;

@Facade(command="3010",comment="获取推荐和历史商品名称列表接口")
@Scope("prototype")
public class GetSearchNamesFacade extends AbsFacade {
	@Resource
	private IHistorySeekService historySeekService;
	@Resource
	private IRecommendService recommendService;
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		GetSearchNamesResponseBean responseBean = new GetSearchNamesResponseBean();
		GetSearchNamesRequestBean requestBean = (GetSearchNamesRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		String userId = requestBean.getUserid();
		//构建历史搜索名称
		if(!ObjectTools.isNullByString(userId)){
			List<HistorySeek> histroySeekList = historySeekService.getHistorySeekList(userId);
			List<HistroyNameBean> histroynamelist = new ArrayList<HistroyNameBean>();
			HistroyNameBean historyNameBean = null;
			for(HistorySeek seek:histroySeekList){
				historyNameBean = new HistroyNameBean();
				historyNameBean.setHistroyname(seek.getSearchterms());
				histroynamelist.add(historyNameBean);
			}
			responseBean.setHistroynamelist(histroynamelist);
		}
		
		//构建推荐商品搜索名称
	    List<Recommend> recommendlist = recommendService.getRecommendProdList();
	    List<RecommendNameBean> recommendNamelist = new ArrayList<RecommendNameBean>();
	    RecommendNameBean recommendNameBean = null;
		for(Recommend recommend:recommendlist){
			recommendNameBean = new RecommendNameBean();
			recommendNameBean.setRecommendname(recommend.getProdname());
			recommendNamelist.add(recommendNameBean);
		}
		
		
		responseBean.setRecommendnamelist(recommendNamelist);
		responseParam.getParamdata().add(responseBean);
		
		return responseParam;
		
	}

}




