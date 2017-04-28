package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.HistroyNameBean;
import com.youxianji.facade.product.bean.json.RecommendNameBean;

public class GetSearchNamesResponseBean {
	
    private List<HistroyNameBean> histroynamelist;
	
    private List<RecommendNameBean> recommendnamelist;

	public List<HistroyNameBean> getHistroynamelist() {
		return histroynamelist;
	}

	public void setHistroynamelist(List<HistroyNameBean> histroynamelist) {
		this.histroynamelist = histroynamelist;
	}

	public List<RecommendNameBean> getRecommendnamelist() {
		return recommendnamelist;
	}

	public void setRecommendnamelist(List<RecommendNameBean> recommendnamelist) {
		this.recommendnamelist = recommendnamelist;
	}
	
	
	
}
