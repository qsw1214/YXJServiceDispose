package com.youxianji.service;

import java.util.List;

import com.youxianji.pojo.MemberCardInfo;

public interface IMemberCardInfoService {

	List<MemberCardInfo> findAll();
	
	
	List<MemberCardInfo> findByCardType(String cardType);
	

}
