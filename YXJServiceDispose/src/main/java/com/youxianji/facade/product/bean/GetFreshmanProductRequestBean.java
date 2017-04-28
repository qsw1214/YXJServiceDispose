package com.youxianji.facade.product.bean;

import base.cn.annotation.InterfaceParam;
import base.cn.web.facade.bean.BaseRequest;
@InterfaceParam(command="3006",comment="获取活动查询商品列表")
public class GetFreshmanProductRequestBean extends BaseRequest{
	
	private String flag;//1 新人专享 2 优先推荐（从商品标签）3 下午茶（从类别获得
	private String tag;
	private String tagid;
	private String invitecode;
	private String typeid;
	
	
	
	
	
	
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public String getTagid() {
		return tagid;
	}
	public void setTagid(String tagid) {
		this.tagid = tagid;
	}
	public String getInvitecode() {
		return invitecode;
	}
	public void setInvitecode(String invitecode) {
		this.invitecode = invitecode;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
}
