package com.youxianji.pojo;

import java.util.Date;

public class AppVersion{

	/* 主键 */
	private String id;
	
	/* 版本编号 */
	private String versionNo;
	
	/* 升级状态（0：关闭，1：新建，2：发布） */
	private String status;
	
	/* APP名称 */
	private String appName;
	
	/* APP下载路径 */
	private String appPath;
	
	/* APP类型 1.android 2.ios*/
	private String appType;
	
	/* 升级内容 */
	private String updateContent;
	
	/* 升级时间 */
	private Date updateTime;
	
	/* 操作人员 */
	private String adminUserName;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAppPath() {
		return appPath;
	}

	public void setAppPath(String appPath) {
		this.appPath = appPath;
	}

	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getUpdateContent() {
		return updateContent;
	}

	public void setUpdateContent(String updateContent) {
		this.updateContent = updateContent;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	
	

}
