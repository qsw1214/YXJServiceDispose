package com.youxianji.pojo;

import java.math.BigDecimal;

public class OrderDetail {

	private String detailid;//'订单明细ID',
	private String ordersn;//'订单号',
	private ProdInfo prod;//'商品ID',
	private Integer quantity;//'商品数量',
	private BigDecimal sellprice;//'单价',
	private String prodname;//'商品名称',
	private String isreview;//'是否评价 0.未评价 1.已评价',
	private String redundancefir;//是否为赠品 ‘Y’是赠品
	private String suppilerid;
	
	
	public String getSuppilerid() {
		return suppilerid;
	}
	public void setSuppilerid(String suppilerid) {
		this.suppilerid = suppilerid;
	}
	public String getRedundancefir() {
		return redundancefir;
	}
	public void setRedundancefir(String redundancefir) {
		this.redundancefir = redundancefir;
	}
	public String getDetailid() {
		return detailid;
	}
	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}
	
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public ProdInfo getProd() {
		return prod;
	}
	public void setProd(ProdInfo prod) {
		this.prod = prod;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getSellprice() {
		return sellprice;
	}
	public void setSellprice(BigDecimal sellprice) {
		this.sellprice = sellprice;
	}
	public String getProdname() {
		return prodname;
	}
	public void setProdname(String prodname) {
		this.prodname = prodname;
	}
	public String getIsreview() {
		return isreview;
	}
	public void setIsreview(String isreview) {
		this.isreview = isreview;
	}
	
	
	
	
	
}
