package com.youxianji.pojo;

import java.util.Date;

/**
 * 此类未用,使用ProdEvaluate代替
 * @author Administrator
 *
 */
@Deprecated
public class ProdComment {

	private String commentid;//'商品评价ID',
	private String prodid;//'商品ID',
	private String userid;//'商品评价人',
	private String ordersn;//所属订单',
	private Integer commentlevel;//'评价级别',
	private Date commenttime;//'评价时间',
	private String commentcontent;//'评价内容',
	private String getCommentid() {
		return commentid;
	}
	public void setCommentid(String commentid) {
		this.commentid = commentid;
	}
	public String getProdid() {
		return prodid;
	}
	public void setProdid(String prodid) {
		this.prodid = prodid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getOrdersn() {
		return ordersn;
	}
	public void setOrdersn(String ordersn) {
		this.ordersn = ordersn;
	}
	public Integer getCommentlevel() {
		return commentlevel;
	}
	public void setCommentlevel(Integer commentlevel) {
		this.commentlevel = commentlevel;
	}
	public Date getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(Date commenttime) {
		this.commenttime = commenttime;
	}
	public String getCommentcontent() {
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	
	
	
	
	
	
	
	
}
