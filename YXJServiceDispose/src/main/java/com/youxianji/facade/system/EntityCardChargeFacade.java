package com.youxianji.facade.system;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;

import base.cn.annotation.Facade;
import base.cn.exception.BaseException;
import base.cn.web.facade.AbsFacade;
import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

import com.youxianji.facade.system.bean.EntityCardChargeRequestBean;
import com.youxianji.pojo.YxjUserPrepaycardStock;
import com.youxianji.service.IYxjUserPrepaycardStockService;
import com.youxianji.web.util.ErrorEnum;

@Facade(command="1016",comment="实体卡充值接口")
@Scope("prototype")
public class EntityCardChargeFacade extends AbsFacade{
	@Resource
	private IYxjUserPrepaycardStockService yxjUserPrepaycardStockService;

	private Logger logger = Logger.getLogger(this.getClass());
	
	private final static Object LOCK_OBJECT_STOCK = new Object();
	
	@Override
	public BaseResponse execute(BaseRequest baseRequest) {
		EntityCardChargeRequestBean requestBean = (EntityCardChargeRequestBean)baseRequest;
		BaseResponse responseParam = new BaseResponse();
		
	    
		synchronized (LOCK_OBJECT_STOCK) {
			YxjUserPrepaycardStock stock = yxjUserPrepaycardStockService.getByCardCode(requestBean.getEntitypass());
		    if(stock == null){
		    	ErrorEnum.valueOf("FAIL_5555").changetMessage("该实体卡不存在");
				throw new BaseException(ErrorEnum.FAIL_5555);
		    }
		    if(!"1".equals(stock.getState())){
		    	ErrorEnum.valueOf("FAIL_5555").changetMessage("该实体卡已使用");
				throw new BaseException(ErrorEnum.FAIL_5555);
		    }
		    if(!"1".equals(stock.getIsenable())){
		    	ErrorEnum.valueOf("FAIL_5555").changetMessage("该实体卡未激活");
				throw new BaseException(ErrorEnum.FAIL_5555);
		    }
		    
		    if("0".equals(stock.getIsrepeat_flag())
		    		&& yxjUserPrepaycardStockService.getByNoRepeat(requestBean.getUserid(), "0") != null){
		    	ErrorEnum.valueOf("FAIL_5555").changetMessage("您已经充值过该类型实体卡");
				throw new BaseException(ErrorEnum.FAIL_5555);
		    }
		    
		    yxjUserPrepaycardStockService.doChargeForUser(stock, requestBean.getUserid());
		}
		return responseParam;
	}

}





























