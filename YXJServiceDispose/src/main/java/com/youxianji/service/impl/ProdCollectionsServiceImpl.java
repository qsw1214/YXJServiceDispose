package com.youxianji.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IProdCollectionsDAO;
import com.youxianji.service.IProdCollectionsService;

@Service("prodCollections")
public class ProdCollectionsServiceImpl implements IProdCollectionsService {

	@Resource
	private IProdCollectionsDAO prodCollectionsDAO;
		
}
