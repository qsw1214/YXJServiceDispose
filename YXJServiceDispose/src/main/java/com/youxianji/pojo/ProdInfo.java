
package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class ProdInfo {

	 private String prodid;//'商品ID',
	 private String prodname;//'商品名称',
	 private String prodnum;//产品编号
	 private BigDecimal sellPrice;//'商品会员价格',
	 private BigDecimal valuePrice;//'商品普通价格'
	 private String typeid;//'商品类别ID',
	 private Integer prodstock;//商品库存',
	 private String prodStockState;//1 库存充足 2 调货中
	 private String imageUrl;//'商品默认图片,
	 private ProdDetailInfo detailInfo;//'商品详情信息ID',
	 private String weight;//'商品重量',
	 private String cpackage;//'商品包装',
	 private String remark;//'商品备注',
	 private String preSellTag;//'销售标记 1.现售 2.预售',
	 private String prodUnit;//'商品单位'
	 private Date preSellTime;//'预售时间'
	 private BusinessUserInfo businessUser;//所属商户
	 private String ishome;//'是否为首页 0.不是  1.是'
	 private String isfreshman;//'是否为新人 0.不是 1.是'
	 private BigDecimal freshmanprice;//新人价格
	 private Integer serialnumber;//首页商品序号
	 private String isactivity;//否为活动商品 0.不是 1.是
	 private String prodDesc;
	 private String state;//1 正常 0 删除 2下架
	 private String payflag;//'配送标记 1.配送商品2.自提商品',
	 private String deductFlag;// 0 非满减商品 1 满减商品
	 
	 private Double taxrate;//'商品缴纳税点  1. 免税 2. 13%税率 3. 17%税率'
	 private String suppilerid;
	 private String stockflag;//'库存标记 1.自有库存 2.非自由库存',
	 private String provenanceflag;//'原产地标记 0.非原产地 1.原产地'
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 

	public Double getTaxrate() {
		return taxrate;
	}
	public void setTaxrate(Double taxrate) {
		this.taxrate = taxrate;
	}
	public String getSuppilerid() {
		return suppilerid;
	}
	public void setSuppilerid(String suppilerid) {
		this.suppilerid = suppilerid;
	}
	public String getStockflag() {
		return stockflag;
	}
	public void setStockflag(String stockflag) {
		this.stockflag = stockflag;
	}
	public String getProvenanceflag() {
		return provenanceflag;
	}
	public void setProvenanceflag(String provenanceflag) {
		this.provenanceflag = provenanceflag;
	}
	public String getDeductFlag() {
		return deductFlag;
	}
	public void setDeductFlag(String deductFlag) {
		this.deductFlag = deductFlag;
	}
	public String getPayflag() {
		return payflag;
	}
	public void setPayflag(String payflag) {
		this.payflag = payflag;
	}
	public String getProdStockState() {
		return prodStockState;
	}
	public void setProdStockState(String prodStockState) {
		this.prodStockState = prodStockState;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getProdnum() {
		return prodnum;
	}
	public void setProdnum(String prodnum) {
		this.prodnum = prodnum;
	}
	public String getProdDesc() {
		return prodDesc;
	}
	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}
	public String getIsactivity() {
		return isactivity;
	}
	public void setIsactivity(String isactivity) {
		this.isactivity = isactivity;
	}
	public Integer getSerialnumber() {
		return serialnumber;
	}
	public void setSerialnumber(Integer serialnumber) {
		this.serialnumber = serialnumber;
	}
	public String getIshome() {
		return ishome;
	}
	public void setIshome(String ishome) {
		this.ishome = ishome;
	}
	public String getIsfreshman() {
		return isfreshman;
	}
	public void setIsfreshman(String isfreshman) {
		this.isfreshman = isfreshman;
	}
	public BigDecimal getFreshmanprice() {
		return freshmanprice;
	}
	public void setFreshmanprice(BigDecimal freshmanprice) {
		this.freshmanprice = freshmanprice;
	}
	public BusinessUserInfo getBusinessUser() {
		return businessUser;
	}
	public void setBusinessUser(BusinessUserInfo businessUser) {
		this.businessUser = businessUser;
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
	public BigDecimal getSellPrice() {
		return sellPrice;
	}
	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}
	public BigDecimal getValuePrice() {
		return valuePrice;
	}
	public void setValuePrice(BigDecimal valuePrice) {
		this.valuePrice = valuePrice;
	}
	public String getTypeid() {
		return typeid;
	}
	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}
	public Integer getProdstock() {
		return prodstock;
	}
	public void setProdstock(Integer prodstock) {
		this.prodstock = prodstock;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public ProdDetailInfo getDetailInfo() {
		return detailInfo;
	}
	public void setDetailInfo(ProdDetailInfo detailInfo) {
		this.detailInfo = detailInfo;
	}
	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getCpackage() {
		return cpackage;
	}
	public void setCpackage(String cpackage) {
		this.cpackage = cpackage;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getPreSellTag() {
		return preSellTag;
	}
	public void setPreSellTag(String preSellTag) {
		this.preSellTag = preSellTag;
	}
	public String getProdUnit() {
		return prodUnit;
	}
	public void setProdUnit(String prodUnit) {
		this.prodUnit = prodUnit;
	}
	public Date getPreSellTime() {
		return preSellTime;
	}
	public void setPreSellTime(Date preSellTime) {
		this.preSellTime = preSellTime;
	}
	
	 
	
	
	
}
