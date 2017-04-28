package com.youxianji.facade.acountWithDraw.bean;

import java.util.List;

import com.youxianji.facade.acountWithDraw.bean.jsonBean.WithdrawcashBandbankBean;

public class GetWithdrawcashBandbankResponseBean {
	
	private List<WithdrawcashBandbankBean> banklist;

	public List<WithdrawcashBandbankBean> getBanklist() {
		return banklist;
	}

	public void setBanklist(List<WithdrawcashBandbankBean> banklist) {
		this.banklist = banklist;
	}

}
