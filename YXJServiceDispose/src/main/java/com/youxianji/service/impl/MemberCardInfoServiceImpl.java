package com.youxianji.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IMemberCardInfoDAO;
import com.youxianji.pojo.MemberCardInfo;
import com.youxianji.service.IMemberCardInfoService;

@Service("memberCardInfoService")
public class MemberCardInfoServiceImpl implements IMemberCardInfoService {

		@Resource
		private IMemberCardInfoDAO memberCardInfoDAO;

		@Override
		public List<MemberCardInfo> findAll() {
			return memberCardInfoDAO.findAll();
		}

		@Override
		public List<MemberCardInfo> findByCardType(String cardType) {

			return memberCardInfoDAO.findByCardType(cardType);
		}
		
		
}
