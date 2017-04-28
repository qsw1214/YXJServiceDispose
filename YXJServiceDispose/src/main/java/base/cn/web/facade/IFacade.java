package base.cn.web.facade;

import base.cn.web.facade.bean.BaseRequest;
import base.cn.web.facade.bean.BaseResponse;

public interface IFacade {
	
	public BaseResponse execute(BaseRequest baseRequest);
}
