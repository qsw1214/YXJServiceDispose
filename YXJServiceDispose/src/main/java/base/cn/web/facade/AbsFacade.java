package base.cn.web.facade;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import base.cn.annotation.Facade;

@Facade(comment="门面抽象类")
public abstract class AbsFacade implements IFacade {
	
	protected HttpServletRequest  request;
	protected HttpServletResponse response;

	public HttpServletRequest getRequest() {
		return request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}
}