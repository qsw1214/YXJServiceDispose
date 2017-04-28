package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class DeductAmtRules {

      private String id;
      private BigDecimal sinceprice;
      private BigDecimal deductamt;
      private Date begintime;
      private Date endtime;
      private String prodid;
      private BigDecimal deductmember;
      
      
      
 
      
      
      
      
		public BigDecimal getDeductmember() {
		return deductmember;
	}
	public void setDeductmember(BigDecimal deductmember) {
		this.deductmember = deductmember;
	}
		public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
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
		public BigDecimal getSinceprice() {
			return sinceprice;
		}
		public void setSinceprice(BigDecimal sinceprice) {
			this.sinceprice = sinceprice;
		}
		public BigDecimal getDeductamt() {
			return deductamt;
		}
		public void setDeductamt(BigDecimal deductamt) {
			this.deductamt = deductamt;
		}
   
      
      
}
	
