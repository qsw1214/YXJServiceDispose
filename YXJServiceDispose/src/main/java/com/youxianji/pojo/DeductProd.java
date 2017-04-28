
package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DeductProd {

	private String id; 
	private BigDecimal sinceamount;
	private String prodid;
	private String state;
	private Date begintime;
	private Date endtime;
	
	
	
	
	public Date getBegintime() {
		return begintime;
	}
	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public BigDecimal getSinceamount() {
		return sinceamount;
	}
	public void setSinceamount(BigDecimal sinceamount) {
		this.sinceamount = sinceamount;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
	
	

	
	
	
}
