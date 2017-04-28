
package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ActivityProdInfo {

	private String activityid; //活动ID
	private ProdInfo prodInfo;//商品ID
	private String title;    //活动标题
	private String content;  //活动内容
	private Date activitybegin; //活动开始时间
	private Date activityend;   //活动结束时间
	private Integer limitquantity;     // 限购数量 默认-1 为无限购 
	private Date createtime; //创建时间
	private Integer forzenquantity;   //冻结数量
	private String buytype; //活动商品类型
	private Integer limitcount;  //单笔限购数量
	private String isonecent;//是否为一分购 0.否 1.是
	private BigDecimal activePrice;
	private String state;// 0删除 1正常
	
	
	
	
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public ProdInfo getProdInfo() {
		return prodInfo;
	}
	public void setProdInfo(ProdInfo prodInfo) {
		this.prodInfo = prodInfo;
	}
	public BigDecimal getActivePrice() {
		return activePrice;
	}
	public void setActivePrice(BigDecimal activePrice) {
		this.activePrice = activePrice;
	}
	public String getIsonecent() {
		return isonecent;
	}
	public void setIsonecent(String isonecent) {
		this.isonecent = isonecent;
	}
	public String getActivityid() {
		return activityid;
	}
	public void setActivityid(String activityid) {
		this.activityid = activityid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getActivitybegin() {
		return activitybegin;
	}
	public void setActivitybegin(Date activitybegin) {
		this.activitybegin = activitybegin;
	}
	public Date getActivityend() {
		return activityend;
	}
	public void setActivityend(Date activityend) {
		this.activityend = activityend;
	}
	public Integer getLimitquantity() {
		return limitquantity;
	}
	public void setLimitquantity(Integer limitquantity) {
		this.limitquantity = limitquantity;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Integer getForzenquantity() {
		return forzenquantity;
	}
	public void setForzenquantity(Integer forzenquantity) {
		this.forzenquantity = forzenquantity;
	}
	public String getBuytype() {
		return buytype;
	}
	public void setBuytype(String buytype) {
		this.buytype = buytype;
	}
	public Integer getLimitcount() {
		return limitcount;
	}
	public void setLimitcount(Integer limitcount) {
		this.limitcount = limitcount;
	}
	
	
	
}
