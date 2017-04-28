package com.youxianji.pojo;

import java.util.Date;

public class YxjBuserWithdrawcashNationalholiday {
	
	private String holidayId; //主键ID
	private String holidayName;//假日名称
	private Date begindate; //法定假日起始日期
	private Date enddate; //法定假日结束日期
	private Date adjustdateone; //调休日一
	private Date adjustdatetwo; //调休日二
	
	
	public String getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(String holidayId) {
		this.holidayId = holidayId;
	}
	public String getHolidayName() {
		return holidayName;
	}
	public void setHolidayName(String holidayName) {
		this.holidayName = holidayName;
	}
	public Date getBegindate() {
		return begindate;
	}
	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public Date getAdjustdateone() {
		return adjustdateone;
	}
	public void setAdjustdateone(Date adjustdateone) {
		this.adjustdateone = adjustdateone;
	}
	public Date getAdjustdatetwo() {
		return adjustdatetwo;
	}
	public void setAdjustdatetwo(Date adjustdatetwo) {
		this.adjustdatetwo = adjustdatetwo;
	}
	
	
}
