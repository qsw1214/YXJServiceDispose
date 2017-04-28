package com.youxianji.facade.product.bean;

import java.util.List;

import com.youxianji.facade.product.bean.json.ProdDetailBean;
import com.youxianji.facade.product.bean.json.ProdImageBean;
import com.youxianji.pojo.ProdTags;


public class GetProdDetailResponseBean{

	private String prodid;//	String	商品编号	
	private String prodname;//	String	商品名称	
	private String unit;//	String	商品售出单位	
	private String valueprice;//	商品原价	
	private String sellprice;//	商品会员价	
	private String activityendtime; //商品活动结束时间
	private String groupbuyid;//商品规则id
	private String groupbuyprice;//商品团购价	
	private String freshflag;
	private String freshprice;
	private String isactivity;//	Boolean	是否有活动	
	private String limitquantity;//	Integer	活动限购数量	-1表示不限数量
	private String buytype;//	String	限购商品类型	1.抢购商品2.单笔限量
	private String sellflag;//	String	销售标记	1.现售2.预售
	private String preselltime;//	String	预售时间 	以天为单位
	private List<ProdImageBean> imageinfo;//	JSON	商品图片列表
	private ProdDetailBean proddetail;//	Json	商品详情信息
	private String proddesc;
	private String monthsellcount;
	private Integer prodstock;//商品库存',
	private String cpackage;//'商品包装',
	private List<ProdTags> prodTagsList;//商品显示标签
	private String detailurl;
	
	
	

	public String getActivityendtime() {
		return activityendtime;
	}
	public void setActivityendtime(String activityendtime) {
		this.activityendtime = activityendtime;
	}
	public String getGroupbuyprice() {
		return groupbuyprice;
	}
	public void setGroupbuyprice(String groupbuyprice) {
		this.groupbuyprice = groupbuyprice;
	}
	public String getDetailurl() {
		return detailurl;
	}
	public void setDetailurl(String detailurl) {
		this.detailurl = detailurl;
	}
	public List<ProdTags> getProdTagsList() {
		return prodTagsList;
	}
	public void setProdTagsList(List<ProdTags> prodTagsList) {
		this.prodTagsList = prodTagsList;
	}
	public Integer getProdstock() {
		return prodstock;
	}
	public void setProdstock(Integer prodstock) {
		this.prodstock = prodstock;
	}
	public String getCpackage() {
		return cpackage;
	}
	public void setCpackage(String cpackage) {
		this.cpackage = cpackage;
	}
	public String getMonthsellcount() {
		return monthsellcount;
	}
	public void setMonthsellcount(String monthsellcount) {
		this.monthsellcount = monthsellcount;
	}
	public String getFreshflag() {
		return freshflag;
	}
	public void setFreshflag(String freshflag) {
		this.freshflag = freshflag;
	}
	public String getFreshprice() {
		return freshprice;
	}
	public void setFreshprice(String freshprice) {
		this.freshprice = freshprice;
	}
	public String getProddesc() {
		return proddesc;
	}
	public void setProddesc(String proddesc) {
		this.proddesc = proddesc;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getValueprice() {
		return valueprice;
	}
	public void setValueprice(String valueprice) {
		this.valueprice = valueprice;
	}
	public String getSellprice() {
		return sellprice;
	}
	public void setSellprice(String sellprice) {
		this.sellprice = sellprice;
	}
	public String getIsactivity() {
		return isactivity;
	}
	public void setIsactivity(String isactivity) {
		this.isactivity = isactivity;
	}
	public String getLimitquantity() {
		return limitquantity;
	}
	public void setLimitquantity(String limitquantity) {
		this.limitquantity = limitquantity;
	}
	public String getBuytype() {
		return buytype;
	}
	public void setBuytype(String buytype) {
		this.buytype = buytype;
	}
	public String getSellflag() {
		return sellflag;
	}
	public void setSellflag(String sellflag) {
		this.sellflag = sellflag;
	}
	public String getPreselltime() {
		return preselltime;
	}
	public void setPreselltime(String preselltime) {
		this.preselltime = preselltime;
	}
	public List<ProdImageBean> getImageinfo() {
		return imageinfo;
	}
	public void setImageinfo(List<ProdImageBean> imageinfo) {
		this.imageinfo = imageinfo;
	}
	public ProdDetailBean getProddetail() {
		return proddetail;
	}
	public void setProddetail(ProdDetailBean proddetail) {
		this.proddetail = proddetail;
	}
	public String getGroupbuyid() {
		return groupbuyid;
	}
	public void setGroupbuyid(String groupbuyid) {
		this.groupbuyid = groupbuyid;
	}
	
}
