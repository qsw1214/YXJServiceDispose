package com.youxianji.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.youxianji.dao.IUserInfoDAO;
import com.youxianji.dao.IYxjBuserGatherInfoDAO;
import com.youxianji.dao.IyxjSystemSmsConfigDAO;
import com.youxianji.facade.yxjia.bean.JIABuserChargeOfflineRequestBean;
import com.youxianji.facade.yxjia.bean.JIABuserChargeOfflineResponseBean;
import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsRequestBean;
import com.youxianji.facade.yxjia.bean.JIAOfflineChargeBillsResponseBean;
import com.youxianji.facade.yxjia.bean.json.JIAChargeBillsBean;
import com.youxianji.network.sms.SmsSendUtil;
import com.youxianji.pojo.UserInfo;
import com.youxianji.pojo.YxjBuserGatherInfo;
import com.youxianji.pojo.yxj_system_sms_config;
import com.youxianji.service.IYxjBuserGatherInfoService;
import com.youxianji.util.Constants;
import com.youxianji.util.DateUtil;
import com.youxianji.util.PropertyManager;
import com.youxianji.util.SMSMessageSendThread;
import com.youxianji.util.bill.BillTransact;
import com.youxianji.util.bill.parambean.CUserParam;
import com.youxianji.util.bill.parambean.InsideBUserParam;
import com.youxianji.util.bill.parambean.InsideGiroParam;
import com.youxianji.web.util.ErrorEnum;
import com.youxianji.web.util.SmsSendType;

import base.cn.exception.BaseException;
import base.cn.util.IdGenerator;
import base.cn.util.ObjectTools;
import base.cn.web.mybatis.plugin.PageInterceptor;
import base.cn.web.mybatis.util.PageInfo;
@Service("yxjBuserGatherInfoService")
public class YxjBuserGatherInfoServiceImpl implements IYxjBuserGatherInfoService {

	static PropertyManager propertyManager = PropertyManager.instance();
	private static String prefix_sn = propertyManager.getValue(Constants.YXJ_PROPERTIES,"offline_charge_prefix");
	
	 
	@Resource
	private IYxjBuserGatherInfoDAO yxjBuserGatherInfoDAO;
	@Resource
	private IUserInfoDAO userInfoDAO;
	@Resource
	private IyxjSystemSmsConfigDAO yxjSystemSmsConfigDAO;
	
	@Override
	public JIABuserChargeOfflineResponseBean doChargeByOffline(JIABuserChargeOfflineRequestBean
			reqeust) throws BaseException {
		JIABuserChargeOfflineResponseBean response = new JIABuserChargeOfflineResponseBean();
	    if(reqeust.getUserid().equals(reqeust.getGatheruserid())){
	    	ErrorEnum.valueOf("FAIL_5555").changetMessage("付款方和收款方不能相同");
			throw new BaseException(ErrorEnum.FAIL_5555);
	    }
		UserInfo user = userInfoDAO.getById(reqeust.getUserid());
		if(!reqeust.getPaypass().equals(user.getPayPass())){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("支付密码不正确");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		
		if(user.getAmount().compareTo(new BigDecimal(reqeust.getPaymoney())) < 0){
			ErrorEnum.valueOf("FAIL_5555").changetMessage("您的用户余额不足");
			throw new BaseException(ErrorEnum.FAIL_5555);
		}
		String operatesn = prefix_sn+IdGenerator.instance().generate("");
		BigDecimal paymoney = new BigDecimal(reqeust.getPaymoney());
		String getheruserid = reqeust.getGatheruserid();
		String userid = reqeust.getUserid();
		
		insideTransferTransact(paymoney,
				userid,getheruserid,operatesn);
		
		YxjBuserGatherInfo gatherInfo = new YxjBuserGatherInfo();
		gatherInfo.setGatherOrdersn(operatesn);
		gatherInfo.setBuserUserid(getheruserid);
		gatherInfo.setCuserUserid(userid);
		gatherInfo.setGatherMoney(paymoney);
		gatherInfo.setGatherRemark(null);
		gatherInfo.setGatherState("1");
		gatherInfo.setGatherTime(new Date());
		
		yxjBuserGatherInfoDAO.insert(gatherInfo);
		String code = SmsSendType.SMS8005.getCode();
		yxj_system_sms_config sysSmsConfigByPointCode = yxjSystemSmsConfigDAO.getSysSmsConfigByPointCode(code);
		String agentCode = "";
		String channel = "";
		if(!ObjectTools.isNullByObject(sysSmsConfigByPointCode)){
			agentCode = sysSmsConfigByPointCode.getSmsChannel().getChannelCode();
			channel = sysSmsConfigByPointCode.getChannelType();
		}
		//发送短信
		SMSMessageSendThread smsThread = new SMSMessageSendThread(agentCode,channel,userInfoDAO.getById(getheruserid).getTelephone()
				,"您的优鲜季账户存入"+paymoney+"元");
		smsThread.start();
		
		return response;
	}
	
	public void insideTransferTransact(BigDecimal paymoney,String userId,String gatheruserid,String operatesn){
		
        CUserParam cuserParam = new CUserParam();
		cuserParam.setUserId(userId);
		cuserParam.setOperatesn(operatesn);
		cuserParam.setOperatemoney(paymoney);
		cuserParam.setBillsflag("1");
		cuserParam.setOperatedesc("线下付款");
		
		InsideBUserParam insideBUserParam = new InsideBUserParam();
		insideBUserParam.setBillsflag("0");
		insideBUserParam.setBuserId(gatheruserid);
		insideBUserParam.setOperatedesc("线下收款");
		insideBUserParam.setOperatemoney(paymoney);
		insideBUserParam.setOperatesn(operatesn);
		
		InsideGiroParam insideGiroParam = new InsideGiroParam();
		insideGiroParam.setCuserParam(cuserParam);
		insideGiroParam.setInsideBuserParam(insideBUserParam);
		
		BillTransact.insideTransferTransact(insideGiroParam);
	}

	@Override
	public JIAOfflineChargeBillsResponseBean getOfflineChargeBills(
			JIAOfflineChargeBillsRequestBean request) throws BaseException {
		JIAOfflineChargeBillsResponseBean response = new JIAOfflineChargeBillsResponseBean();
		
		PageInterceptor.startPage(Integer.parseInt(request.getPage()));
		yxjBuserGatherInfoDAO.getList(request.getUserid());
	    @SuppressWarnings("unchecked")
	    PageInfo<YxjBuserGatherInfo> pageInfo = PageInterceptor.endPage();
		
	    bulidJIAOfflineChargeBillsResponseBean(response, pageInfo);
		
		
		
		
		return response;
	}
	
	
	private JIAOfflineChargeBillsResponseBean bulidJIAOfflineChargeBillsResponseBean(
			JIAOfflineChargeBillsResponseBean responseBean,PageInfo<YxjBuserGatherInfo> pageInfo){
		
		responseBean.setCurrentpage(pageInfo.getPageIndex().toString());
		responseBean.setPagecount(pageInfo.getPageSize().toString());
		responseBean.setTotalcount(pageInfo.getRecordCount().toString());
		responseBean.setTotalpage(pageInfo.getTotalpage().toString());
		
		List<JIAChargeBillsBean> jsonlist = new ArrayList<JIAChargeBillsBean>();
		JIAChargeBillsBean pvBean = null;
		for(YxjBuserGatherInfo pv:pageInfo.getList()){
			pvBean = new JIAChargeBillsBean();
			pvBean.setGathermoney(pv.getGatherMoney().toString());
			pvBean.setGathertime(DateUtil.toStr(pv.getGatherTime(), "yyyy-MM-dd HH:mm:ss"));
			pvBean.setOrderstate(pv.getGatherState());
			pvBean.setOrdersn(pv.getGatherOrdersn());
			UserInfo u = userInfoDAO.getById(pv.getCuserUserid());
			if(u != null){
				pvBean.setPaytelephone(u.getTelephone());
			}
			
			pvBean.setRemark(pv.getGatherRemark());
			
			jsonlist.add(pvBean);
		}
		
		responseBean.setJsonlist(jsonlist);
		
		return responseBean;
	}
	

}
