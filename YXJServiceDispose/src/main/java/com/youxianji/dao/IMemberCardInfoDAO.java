package com.youxianji.dao;

import java.util.List;

import com.youxianji.pojo.MemberCardInfo;



public interface IMemberCardInfoDAO {

	List<MemberCardInfo> findAll();
	
	public MemberCardInfo findMemberCardInfoById(String id);
	
	List<MemberCardInfo> findByCardType(String cardType);
	
	 	
	
}
