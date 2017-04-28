package com.youxianji.pojo;

import java.util.Date;

public class YxjUserProdevaluateReply {

	private String replyId;
	private String replyName;
	private String replyAuserid;
	private Date replyTime;
	private String replyContent;
	private ProdEvaluate comment;
	private String replyState;//0 删除 1 正常
	
	
	public String getReplyId() {
		return replyId;
	}
	public void setReplyId(String replyId) {
		this.replyId = replyId;
	}
	public String getReplyName() {
		return replyName;
	}
	public void setReplyName(String replyName) {
		this.replyName = replyName;
	}
	public String getReplyAuserid() {
		return replyAuserid;
	}
	public void setReplyAuserid(String replyAuserid) {
		this.replyAuserid = replyAuserid;
	}
	public Date getReplyTime() {
		return replyTime;
	}
	public void setReplyTime(Date replyTime) {
		this.replyTime = replyTime;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	
	public ProdEvaluate getComment() {
		return comment;
	}
	public void setComment(ProdEvaluate comment) {
		this.comment = comment;
	}
	public String getReplyState() {
		return replyState;
	}
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	
	
}
