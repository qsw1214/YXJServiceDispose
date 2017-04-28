package com.youxianji.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class YxjUserPrepaycardStock {

	  private String cardCode;//`card_code` varchar(50) COLLATE utf8_bin NOT NULL COMMENT '卡编号',
	  private String user;//`user_info_id` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '用户ID',
	  private String batNo;//`bat_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '入库批次号',
	  private String cardName;//`card_name` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '预付卡名称',
	  private String faceValue;//`face_value` int(11) DEFAULT NULL COMMENT '卡信息ID',
	  private String cardNo;//`card_no` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '卡号',
	  private Date createTime;//`create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
	  private Date receiveTime;//`receive_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '领取时间',
	  private String description;//`description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '卡描述',
	  private BigDecimal rebate;//`rebate` decimal(19,2) DEFAULT NULL COMMENT '折扣率',
	  private String state;//`state` char(1) COLLATE utf8_bin DEFAULT NULL COMMENT '状态 1.可使用 2.已用完 3.作废',
	  private String isenable;
	  private String isrepeat_flag;
	  
	  
	  
	  
	  public String getIsrepeat_flag() {
		return isrepeat_flag;
	}
	public void setIsrepeat_flag(String isrepeat_flag) {
		this.isrepeat_flag = isrepeat_flag;
	}
	public String getIsenable() {
		return isenable;
	}
	public void setIsenable(String isenable) {
		this.isenable = isenable;
	}
	public String getCardCode() {
		return cardCode;
	}
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getBatNo() {
		return batNo;
	}
	public void setBatNo(String batNo) {
		this.batNo = batNo;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getFaceValue() {
		return faceValue;
	}
	public void setFaceValue(String faceValue) {
		this.faceValue = faceValue;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public BigDecimal getRebate() {
		return rebate;
	}
	public void setRebate(BigDecimal rebate) {
		this.rebate = rebate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
   
	  
	  
}
	
