package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IGroupbuyDAO;
import com.youxianji.pojo.Groupbuy;
import com.youxianji.service.IGroupbuyService;

@Service("groupbuyService")
public class GroupbuyServiceImpl implements IGroupbuyService {
	@Resource
	private IGroupbuyDAO groupbuyDAO;

	@Override
	public Groupbuy getGroupbuyByDate() {
		Groupbuy groupbuy=null;
		List<Groupbuy> groupbuyList=groupbuyDAO.getGroupbuyByDate();
		if(groupbuyList!=null&&groupbuyList.size()>0){
			groupbuy=groupbuyList.get(0);
		}
		return groupbuy;
	}

}