package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.ICommentPicDAO;
import com.youxianji.service.ICommentPicService;

@Service("commentPic")
public class CommentPicServiceImpl implements ICommentPicService {

	@Resource
	private ICommentPicDAO commentPicDAO;
	
	
	
}
